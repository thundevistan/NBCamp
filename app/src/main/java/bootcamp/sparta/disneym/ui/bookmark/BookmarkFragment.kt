package bootcamp.sparta.disneym.ui.bookmark

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import bootcamp.sparta.disneym.R
import bootcamp.sparta.disneym.databinding.FragmentBookmarkBinding
import bootcamp.sparta.disneym.model.BookmarkModel
import bootcamp.sparta.disneym.viewmodel.bookmark.BookmarkViewModel

/*
* 작성자: 서정한
* 내용: DetailPage에서 북마크로 지정한 item들을 보여주고
* 선택한 아이템을 삭제하는 기능이 있습니다.
* */
class BookmarkFragment : Fragment() {
    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy {
        BookmarkListAdapter(onItemChecked = { position, item ->
            updateItemChecked(position, item)
        })
    }
    private val viewModel: BookmarkViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
    }

    private fun initView() = with(binding) {
        bookmarkRecyclerview.adapter = adapter
        // Edit
        bookmarkEditTextview.setOnClickListener {
            val type: BookmarkViewType = BookmarkViewType.Edit
            changeVisibleForType(type)
            updateBookmarkListType(type)
        }
        // Close
        bookmarkCloseButton.setOnClickListener {
            val type: BookmarkViewType = BookmarkViewType.Normal
            changeVisibleForType(type)
            updateBookmarkListType(type)
        }
        // Remove
        bookmarkRemoveButton.setOnClickListener {
            removeDialog(requireContext())
        }
    }

    private fun initViewModel() = with(viewModel) {
        list.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    // Edit Mode Selector. Edit버튼을 클릭할경우 EditMode로 View를 업데이트하여 보여줍니다.
    // Close 버튼을 클릭할경우 Edit모드가 종료되고 UI를 업데이트 합니다.
    private fun changeVisibleForType(type: BookmarkViewType) = with(binding) {
        when (type) {
            BookmarkViewType.Edit -> {
                bookmarkCloseButton.visibility = View.VISIBLE
                bookmarkRemoveButton.visibility = View.VISIBLE
                bookmarkEditTextview.visibility = View.INVISIBLE
            }

            BookmarkViewType.Normal -> {
                bookmarkEditTextview.visibility = View.VISIBLE
                bookmarkCloseButton.visibility = View.INVISIBLE
                bookmarkRemoveButton.visibility = View.INVISIBLE
            }
        }
    }

    // Edit모드 여부에 따라 item의 viewType 데이터를 변경해줍니다.
    private fun updateBookmarkListType(type: BookmarkViewType) {
        viewModel.updateBookmarkListType(type)
    }

    // 체크박스상태 업데이트
    private fun updateItemChecked(position: Int, item: BookmarkModel) {
        viewModel.updateItemChecked(position, item)
    }

    // 삭제버튼 Dialog
    private fun removeDialog(context: Context) {
        val current = viewModel.list.value.orEmpty().toMutableList()
        val removes = ArrayList<BookmarkModel>()
        removes.addAll(current.filter { it.isChecked })

        if (removes.isEmpty()) {
            return
        }

        var builder = AlertDialog.Builder(context)
        builder.setTitle("삭제")
        builder.setMessage("정말 삭제하시겠습니까?")
        builder.setIcon(R.mipmap.ic_launcher)

        // 버튼 클릭시에 무슨 작업을 할 것인가!
        val listener = DialogInterface.OnClickListener { p0, p1 ->
            when (p1) {
                DialogInterface.BUTTON_POSITIVE -> {
                    for (i in removes.indices) {
                        viewModel.removeSelectedBookmarkItem(removes[i])
                    }
                }

                DialogInterface.BUTTON_NEGATIVE -> {}
            }
        }
        builder.setPositiveButton("확인", listener)
        builder.setNegativeButton("취소", listener)

        builder.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}