<div align="center">
  
  ![header](https://capsule-render.vercel.app/api?type=Waving&height=200&text=thundevistan&fontColor=d5e6f5&color=timeGradient&animation=fadeIn)
</div>

# 4주차 과제

### **과제: 간단한 뉴스 리더 앱 만들기**

**목표**: 두 개의 Fragment를 사용하여 간단한 뉴스 리더 앱을 만들기

기간 : 9월 7일 오전 10시까지

<br>

data class NewsItem(

val title : String, // This is title

val article : String // This is article

)

<br>

1. **TitleFragment**: 여러 뉴스 기사의 제목을 표시하는 리스트를 포함하고 있습니다.
2. **DetailFragment**: 사용자가 TitleFragment에서 기사 제목을 클릭하면 해당 기사의 내용을 표시합니다.

**세부 사항**:

1. **MainActivity**에는 두 개의 Fragment를 호스팅하는 레이아웃이 포함되어야 합니다.
    - 화면이 세로 방향일 때는 TitleFragment만 표시되며, 기사 제목을 클릭하면 DetailFragment로 교체되어야 합니다.
        - 추가 선택과제
            - 화면이 가로 방향일 때는 TitleFragment와 DetailFragment가 동시에 표시되어야 합니다.
2. **TitleFragment**:
    - RecyclerView를 사용하여 기사 제목을 표시하세요.
    - 기사 제목을 클릭하면 해당 기사의 세부 내용을 DetailFragment에서 표시해야 합니다.
        - 추가 선택
            - Bundle을 통해 DetailFragment 에 기사 데이터 전달
3. **DetailFragment**:
    - 전달받은 기사의 세부 내용을 TextView에 표시하세요.
4. 기사의 제목 및 내용은 임의로 설정하거나, 더미 데이터를 사용하세요.

<br>

**힌트**:

1. **TitleFragment에서 DetailFragment로 데이터 전달하기**:
    
    ```kotlin
    val articleDetail = "기사의 세부 내용" // 예시 내용
    val detailFragment = DetailFragment()
    val bundle = Bundle()
    bundle.putString("articleDetail", articleDetail)
    detailFragment.arguments = bundle
    ```
    

**DetailFragment에서 Bundle로부터 데이터 받기**:

  ```
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_detail, container, false)
    val textView: TextView = view.findViewById(R.id.articleDetailTextView)
    
    arguments?.let {
        val articleDetail = it.getString("articleDetail")
        textView.text = articleDetail
    }   
    return view
}
  ```
