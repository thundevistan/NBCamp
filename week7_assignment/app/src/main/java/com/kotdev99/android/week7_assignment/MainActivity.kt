package com.kotdev99.android.week7_assignment

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings.ACTION_SETTINGS
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotdev99.android.week7_assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
	private lateinit var resultLauncher: ActivityResultLauncher<Intent>

	private val requestNotificationPermissionLauncher =
		registerForActivityResult(
			ActivityResultContracts.RequestPermission()
		) { ok ->
			if (ok) {
				// 알림 권한 허용
			} else {
				// 알림 권한 거절
			}
		}

	@SuppressLint("NotifyDataSetChanged")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		val productList = productList()

		val adapter = ProductAdapter(productList)
		binding.rvMain.adapter = adapter
		binding.rvMain.layoutManager = LinearLayoutManager(this)
		binding.rvMain.setHasFixedSize(true)    // RecyclerView 사이즈 고정
		binding.rvMain.addItemDecoration(
			DividerItemDecoration(
				this, LinearLayoutManager.VERTICAL  // Divider
			)
		)

		adapter.apply {
			itemClick = object : ProductAdapter.ItemClick {
				override fun onClick(view: View, position: Int) {
					val intent = Intent(this@MainActivity, DetailActivity::class.java)
					intent.putExtra("INDEX", position)
					intent.putExtra("product", productList[position])
					resultLauncher.launch(intent)
				}
			}
			itemLongClick = object : ProductAdapter.ItemLongClick {
				@SuppressLint("NotifyDataSetChanged")
				override fun onLongClick(view: View, position: Int) {
					var builder = AlertDialog.Builder(this@MainActivity)
					builder.setIcon(R.drawable.ic_chat)
					builder.setTitle("상품 삭제")
					builder.setMessage("상품을 정말로 삭제하시겠습니까?")

					val listener = DialogInterface.OnClickListener { _, p1 ->
						when (p1) {
							DialogInterface.BUTTON_POSITIVE -> {
								productList.removeAt(position)
								notifyDataSetChanged()
							}
						}
					}

					builder.setPositiveButton("확인", listener)
					builder.setNegativeButton("취소", listener)

					builder.show().apply {
						findViewById<TextView>(android.R.id.button1)?.apply {
							setTextColor(context.getColor(android.R.color.holo_purple))
						}
						findViewById<TextView>(android.R.id.button2)?.apply {
							setTextColor(context.getColor(android.R.color.holo_purple))
						}
					}
				}
			}
		}

		binding.ivNoti.setOnClickListener {
			requestNotificationPermission()
			notification()
		}

		resultLauncher =
			registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
				if (result.resultCode == RESULT_OK) {
					val position: Int = result.data?.getIntExtra("INDEX",0) as Int
					val isLike: Boolean = result.data?.getBooleanExtra("isLike", false) as Boolean

					if (isLike) {
						productList[position].isFav = true
						productList[position].fav += 1
					} else {
						if (productList[position].isFav) {
							productList[position].isFav = false
							productList[position].fav -= 1
						}
					}
				}
				adapter.notifyDataSetChanged()
			}

		fabClick()
	}

	// Notification 권한
	private fun requestNotificationPermission() {
		if (
			ContextCompat.checkSelfPermission(
				this,
				Manifest.permission.POST_NOTIFICATIONS
			) != PackageManager.PERMISSION_GRANTED
		) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
				if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
					Toast.makeText(
						this, "알림을 받으려면 권한을 허용해 주세요!", Toast.LENGTH_SHORT
					).show()
				} else {
					requestNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
				}
			} else {
				Toast.makeText(
					this, "알림을 받으려면 권한을 허용해 주세요!", Toast.LENGTH_SHORT
				).show()

				val intent = Intent(ACTION_SETTINGS)
				startActivity(intent)
			}
		}
	}

	// Notification
	private fun notification() {
		val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

		val builder: NotificationCompat.Builder
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			val channelId = "channel-apple"
			val channelName = "Apple Market"
			val channel = NotificationChannel(
				channelId,
				channelName,
				NotificationManager.IMPORTANCE_DEFAULT
			).apply {
				description = "Apple channel Description"
				setShowBadge(true)
				val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
				val audioAttributes = AudioAttributes.Builder()
					.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
					.setUsage(AudioAttributes.USAGE_ALARM)
					.build()
				setSound(uri, audioAttributes)
				enableVibration(true)
			}
			manager.createNotificationChannel(channel)
			builder = NotificationCompat.Builder(this, channelId)
		} else {
			builder = NotificationCompat.Builder(this)
		}

		builder.run {
			setSmallIcon(R.drawable.ic_noti_circle)
			setContentTitle("키워드 알림")
			setContentText("설정한 키워드에 대한 알림이 도착했습니다!!")
		}
		manager.notify(11, builder.build())
	}

	// FAB
	private fun fabClick() {
		val fadeIn = AlphaAnimation(0f, 1f).apply { duration = 500 }
		val fadeOut = AlphaAnimation(1f, 0f).apply { duration = 500 }
		var isTop = true

		binding.rvMain.addOnScrollListener(object : RecyclerView.OnScrollListener() {
			override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
				super.onScrollStateChanged(recyclerView, newState)
				if (!binding.rvMain.canScrollVertically(-1)
					&& newState == RecyclerView.SCROLL_STATE_IDLE
				) {
					binding.fab.startAnimation(fadeOut)
					binding.fab.visibility = View.GONE
					isTop = true
					Log.d("isTop?", "True")
				} else {
					if (isTop) {
						binding.fab.visibility = View.VISIBLE
						binding.fab.startAnimation(fadeIn)
						isTop = false
						Log.d("isTop?", "False")
					}
				}
			}
		})
		binding.fab.setOnClickListener {
			binding.rvMain.smoothScrollToPosition(0)
		}
	}

	// Back 버튼
	@Deprecated("Deprecated in Java")
	override fun onBackPressed() {
		dialog()
	}

	// Back 버튼 다이얼로그
	private fun dialog() {
		val builder = AlertDialog.Builder(this)
		builder.setIcon(R.drawable.ic_chat)
		builder.setTitle("종료")
		builder.setMessage("정말 종료하시겠습니까?")

		val listener = DialogInterface.OnClickListener { _, p1 ->
			when (p1) {
				DialogInterface.BUTTON_POSITIVE -> finish()
			}
		}
		builder.setPositiveButton("확인", listener)
		builder.setNegativeButton("취소", listener)

		builder.show()
	}

	// 원본 데이터 준비
	private fun productList(): ArrayList<Product> {

		val productList = ArrayList<Product>()

		productList.add(
			Product(
				1,
				R.drawable.sample1,
				"산지 한달된 선풍기 팝니다",
				"이사가서 필요가 없어졌어요 급하게 내놓습니다",
				"대현동",
				1000,
				"서울 서대문구 창천동",
				13,
				25
			)
		)
		productList.add(
			Product(
				2,
				R.drawable.sample2,
				"김치냉장고",
				"이사로인해 내놔요",
				"안마담",
				20000,
				"인천 계양구 귤현동",
				8,
				28
			)
		)
		productList.add(
			Product(
				3,
				R.drawable.sample3,
				"샤넬 카드지갑",
				"고퀄지갑이구요\n" +
						"사용감이 있어서 싸게 내어둡니다",
				"코코유",
				10000,
				"수성구 범어동",
				23,
				5
			)
		)
		productList.add(
			Product(
				4,
				R.drawable.sample4,
				"금고",
				"금고\n" +
						"떼서 가져가야함\n" +
						"대우월드마크센텀\n" +
						"미국이주관계로 싸게 팝니다",
				"Nicole",
				10000,
				"해운대구 우제2동",
				14,
				17
			)
		)
		productList.add(
			Product(
				5,
				R.drawable.sample5,
				"갤럭시Z플립3 팝니다",
				"갤럭시 Z플립3 그린 팝니다\n" +
						"항시 케이스 씌워서 썻고 필름 한장챙겨드립니다\n" +
						"화면에 살짝 스크래치난거 말고 크게 이상은없습니다!",
				"절명",
				150000,
				"연제구 연산제8동",
				22,
				9
			)
		)
		productList.add(
			Product(
				6,
				R.drawable.sample6,
				"프라다 복조리백",
				"까임 오염없고 상태 깨끗합니다\n" +
						"정품여부모름",
				"미니멀하게",
				50000,
				"수원시 영통구 원천동",
				25,
				16
			)
		)
		productList.add(
			Product(
				7,
				R.drawable.sample7,
				"울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장",
				"울산 동해바다뷰 60평 복층 펜트하우스 1일 숙박권\n" +
						"(에어컨이 없기에 낮은 가격으로 변경했으며 8월 초 가장 더운날 다녀가신 분 경우 시원했다고 잘 지내다 가셨습니다)\n" +
						"1. 인원: 6명 기준입니다. 1인 10,000원 추가요금\n" +
						"2. 장소: 북구 블루마시티, 32-33층\n" +
						"3. 취사도구, 침구류, 세면도구, 드라이기 2개, 선풍기 4대 구비\n" +
						"4. 예약방법: 예약금 50,000원 하시면 저희는 명함을 드리며 입실 오전 잔금 입금하시면 저희는 동.호수를 알려드리며 고객님은 예약자분 신분증 앞면 주민번호 뒷자리 가리시거나 지우시고 문자로 보내주시면 저희는 카드키를 우편함에 놓아 둡니다.\n" +
						"5. 33층 옥상 야외 테라스 있음, 가스버너 있음\n" +
						"6. 고기 굽기 가능\n" +
						"7. 입실 오후 3시, 오전 11시 퇴실, 정리, 정돈 , 밸브 잠금 부탁드립니다.\n" +
						"8. 층간소음 주의 부탁드립니다.\n" +
						"9. 방3개, 화장실3개, 비데 3개\n" +
						"10. 저희 집안이 쓰는 별장입니다.",
				"굿리치",
				150000,
				"남구 옥동",
				142,
				54
			)
		)
		productList.add(
			Product(
				8,
				R.drawable.sample8,
				"샤넬 탑핸들 가방",
				"샤넬 트랜디 CC 탑핸들 스몰 램스킨 금장 플립백 !\n" +
						"\n" +
						"색상 : 블랙\n" +
						"사이즈 : 25.5cm * 17.5cm * 8cm\n" +
						"구성 : 본품더스트\n" +
						"\n" +
						"급하게 돈이 필요해서 팝니다 ㅠ ㅠ",
				"난쉽",
				180000,
				"동래구 온천제2동",
				31,
				7
			)
		)
		productList.add(
			Product(
				9,
				R.drawable.sample9,
				"4행정 엔진분무기 판매합니다.",
				"3년전에 사서 한번 사용하고 그대로 둔 상태입니다. 요즘 사용은 안해봤습니다. 그래서 저렴하게 내 놓습니다. 중고라 반품은 어렵습니다.\n",
				"알뜰한",
				30000,
				"원주시 명륜2동",
				7,
				28
			)
		)
		productList.add(
			Product(
				10,
				R.drawable.sample10,
				"셀린느 버킷 가방",
				"22년 신세계 대전 구매입니당\n" +
						"\"셀린느 버킷백\n\"" +
						"\"구매해서 몇번사용했어요\n\"" +
						"\"까짐 스크래치 없습니다.\n\"" +
						"\"타지역에서 보내는거라 택배로 진행합니당!\"",
				"똑태현",
				190000,
				"중구 동화동",
				40,
				6
			)
		)
		return productList
	}
}