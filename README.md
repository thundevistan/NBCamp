# Disney-

![header](https://capsule-render.vercel.app/api?type=soft&color=0:05004e,100:4437ff&height=200&section=header&text=Disney-&fontColor=fffafa&fontSize=90&gradation=vertical)

## 🎬 목차 
***A. 팀원 구성*** </br>

***B. 프로젝트 소개*** </br>
  1. Project </br>
  2. WireFrame <br/>
  3. Style </br>
  4. Architecture </br>
  5. Tech </br>
  6. File Structure </br>
  7. Showcase

***C. Team Notion***


## 👨‍👨‍👦‍👦 Team Members

|           | 이름   | 역할 | MBTI        | GITHUB                                        |
| ----------| ------ | ---- | ----------- | ------------------------------------------- |
| <img src="https://github.com/boomshh.png" width="30" height="30"> | 김현준 | 팀장 | ISFP | [보러가기](https://github.com/boomshh) |
| <img src="https://avatars.githubusercontent.com/u/132810978?v=4" width="30" height="30"> | 추민수 | 팀원 | ENFJ | [보러가기](https://github.com/SoftyChoo) |
| <img src="https://avatars.githubusercontent.com/u/76927858?v=4" width="30" height="30"> | 서정한 | 팀원 | ISTJ | [보러가기](https://github.com/seojh5939)   |
| <img src="https://avatars.githubusercontent.com/u/139092551?v=4" width="30" height="30"> | 김민준 | 팀원 | ENTP | [보러가기](https://github.com/thundevistan)  |

## Project 

팀 프로젝트 명은 Disney-로, Disney+ 를 본따 유튜브 채널로 만든 미디어 어플입니다. 
YouTube API를 활용하여 사용자에게 원하는 동영상을 쉽게 제공할 수 있는 깔끔한 미디어 앱을 개발하는 것을 목표로 했으며, MVVM 아키텍처와 Clean Architecture 원칙을 따라 구현되었습니다.

- [ ] Youtube API를 사용하여 Json 파일과 매핑한 유튜브 검색, 동영상, 카테고리 정보들을 가져옵니다. <br/>
- [ ] 각 카테고리 파라미터를 mostPopular로 설정하였기에 원하는 카테고리 클릭 시 카테고리 별 인기 동영상을 보여줍니다. <br/>
- [ ] 검색을 통해 키워드에 맞는 동영상을 보여줍니다. <br/>
- [ ] 이미지 또는 검색을 통한 동영상을 클릭하면 상세 페이지로 넘어가며 영상에 대한 자세한 정보를 확인할 수 있습니다. 또, 상세 페이지에서는 동영상 시청, 저장함에 보관, 다른 유저들에게 공유할 수 있습니다. <br/>
- [ ] 보관함 페이지에서 수정 버튼을 통해 보관함에 저장된 영상들을 관리할 수 있습니다. <br/>
- [ ] 마이 페이지에서 프로필 사진, 이메일, 비밀번호를 변경할 수 있습니다. <br/>
- [ ] 보관함으로 저장된 영상들과 변경된 사진, 이메일, 비밀번호는 SharedPreference로 저장되며 앱이 종료되더라도 재실행 시 로드됩니다.

## WireFrame 

<img width="1458" alt="wireFrame" src="https://github.com/Disneym/Disneym/assets/133577209/aa8c0d5f-e619-43dd-b280-a660a5090b16">

## Style / Logo 
- 실제 Disney+ 어플의 색상과 이미지를 참조하여 만들었습니다.
  
<img height="400" src="https://github.com/Disneym/Disneym/assets/133577209/36712213-2060-4e53-a20a-bd6739106b42">
<img width="226" alt="스크린샷 2023-10-06 오전 11 34 03" src="https://github.com/Disneym/Disneym/assets/132810978/81ef2363-5764-4d28-8e0f-28810502bb52">

## Architecture
### MVVM / Clean Architecture
- 유지보수성과 확장성을 위한 MVVM 구조 <br/>
- 로직을 사용자 인터페이스 제어에서 분리하기 위해 UI, Domain, Data 3개의 Layer로 나누어 의존성 관리

## Tech 

### Android Jetpack 
  - `ViewModel`
  - `LiveData`
  - `DataBinding`
  - `ViewBinding`
  - `SharedPreferences`

### Android Libraries
  - `Glide`
  - `Retrofit2`
  - `Gson`
  - `Kotlin Coroutine`

### UI Frameworks and Components
  - `XML Layouts`
  - `RecyclerView`
  - `ViewPager2`
  - `TabLayout`
  - `SearchView`
  - `Material Design`
  - `Fragment`

### Data Source 
  - [YouTube Data API v3](https://console.cloud.google.com/marketplace/product/google/youtube.googleapis.com?hl=ko&project=disneym)

## File Structure
- `data`
  - `datasource.remote/`
    - `YoutubeApi` : Retrofit API 인터페이스를 정의 
    - `YoutubeRetrofit` : Youtube API 인터페이스의 구현체를 생성하고 반환
  - `modle/`
    - `CategoryModel.kt` : 카테고리 정보를 받아오는 데이터 모델
    - `ChannelModel.kt`: 채널 정보를 받아오는 데이터 모델
    - `SearchEntity` : 검색 정보를 받아오는 데이터 모델
    - `VideoModel` : 동영상 정보를 받아오는 데이터 모델
  - `repository/`
    - `SearchRepositoryImpl` : Retrofit을 사용하여 검색 요청 후 응답 반환 
    - `MyPageRepositoryImpl` : 실질적인 데이터를 관리하는 Repository 구현부 / SharedPreferences로 나의 정보를 저장 
    - `HomeRepositoryImpl` : Retrofit을 사용하여 데이터 요청 후 응답 반환 
- `domain/`
  - `repository/`
    - `MyPageRepository` : 마이 페이지 정보 관련 데이터를 처리하는 메서드를 정의
    - `HomeRepository` : 동영상, 채널, 카테고리 관련 데이터를 처리하는 메서드를 정의 
    - `SearchRepository` : 검색 관련 데이터를 처리하는 메서드를 정의(검색 결과를 가져온다)
  - `usecase/`
    - `home/`
      - `GetVideoUseCase` : HomeRepository를 주입받아 동영상 작업을 수행
    - `mypage/`
      - `LoadUserDataUseCase` : MyPageRepositoryImpl를 주입받아 사용자 데이터를 로드하는 작업을 처리한다. 
      - `SaveUserDataUseCase` : MyPageRepositoryImpl를 주입받아 사용자 데이터를 저장하는 작업을 처리한다. 
      - `UpdateUserIdUseCase` : MyPageRepositoryImpl를 주입받아 사용자의 아이디를 업데이트 하는 작업을 처리한다. 
      - `UpdateUserProfileUseCase` : MyPageRepositoryImpl를 주입받아 사용자의 프로필을 업데이트하는 작업을 처리한다. 
      - `UpdateUserPwUseCase` : MyPageRepositoryImpl를 주입받아 사용자의 패스워드를 업데이트하는 작업을 처리한다. 
    - `search/`
      - `SearchUseCase` : SearchRepository를 주입받아 검색 작업을 수행 
  - `Model/` : 모든 모델들을 `DetailModel`로 변환하기 위해 확장 함수 `toDetailModel` 제공. 
    - `BookmarkModel` : 보관함 데이터 모델(확장 함수를 이용해 `HomeModel`로도 변환)
    - `DetailModel` : 상세 페이지 데이터 모델(확장 함수를 이용해 `BookMarkModel`, `HomeModel`로 변환)
    - `HomeModel` : 홈 페이지 데이터 모델(확장 함수를 이용해 `BookMarkModel`, `DetailModel`로 변환)
    - `SearchModel` : 검색 데이터 모델. 
- `ui/`
  - `bookmark/`
    - `BookMarkFragment` : 보관함 UI 및 로직 관련 
    - `BookMarkListAdapter` : ListAdapter를 상속하며 편집 상태에 맞는 뷰 타입에 따른 뷰홀더 생성하는 어댑터
    - `BookMarkViewType` : 뷰 타입을 구분하기 위해 사용하는 sealed class
  - `detail/`
    - `DetailActivity` : 디테일 프래그먼트를 초기화하고 상세 정보를 표시하는 화면을 관리 
    - `DetailFragment` : 상세 페이지 UI 및 로직 관련 
  - `home/`
    - `HomeFragment` : 홈 화면 UI 및 로직 관련
    - `HomeRecyclerAdapter` : 홈 화면의 리싸이클러뷰 아이템을 표시하고 관리하는 어댑터
    - `HomeViewPagerAdapter` : 홈 화면 상단에 위치한 뷰 페이저의 아이템을 표시하고 관리하는 어댑터
  - `main/`
    - `MainActivity` : 앱의 메인 화면을 구성 
    - `ViewPagerAdapter` : MainActivity에서 사용하는 뷰 페이저 어댑터 
  - `mypage/`
    - `dialog/`
      - `MyPageProfileDialog` : 프로필 사진 변경 다이얼로그
      - `MyPageTextDialog` : ID와 PW를 수정하기 위한 다이얼로그 
      - `ProfileDialogModel` : 프로필 이미지를 전달하기 위한 모델
      - `ProfileListAdapter` : ListAdapter를 상속하며 프로필 이미지 목록을 표시하기 위한 리싸이클러뷰 어댑터 
    - `EditDialogType` : ID, PW 수정 시 Input Type을 진입점에 따라 다르게 설정해주는 Enum Class
    - `MyPageFragment` : 사용자의 개인 정보 UI 및 로직 관련
    - `UserModel` : 사용자 정보를 나타내는 데이터 모델 
  - `search/`
    - `SearchFragment` : 검색 화면 UI 및 로직 관련
    - `SearchMainAdapter` : ListAdapter를 상속하며 검색 메인 리싸이클러뷰 어댑터
    - `SearchViewAdapter` : ListAdapter를 상속하며 검색 결과를 보여주는 리싸이클러뷰 어댑터 
  - `splash/`
    - `SplashActivity` : 앱의 스플래시 화면을 나타내는 액티비티
  - `viewmodel/`
    - `bookmark/`
      - `BookmarkViewModel` : LiveData를 생성하여 북마크 관련 데이터 및 뷰 업데이트를 관리하는 ViewModel. 내부에 ViewModelFactory 생성.
    - `detail/`
      - `DetailViewModel` : LiveData를 생성하여 상세 페이지 관련 데이터 및 뷰 업데이트를 관리하는 ViewModel.
      - `DetailViewModelFactory` : DetailViewModel 생성을 위한 Factory 클래스
    - `home/`
      - `HomeViewModel` :  LiveData를 생성하여 홈 화면 관련 데이터 및 뷰 업데이트를 관리하는 ViewModel.
      - `HomeViewModelFactory` : HomeViewModel 생성을 위한 Factory 클래스
    - `my/`
      - `MyPageFactory` : MyPageViewModel 생성을 위한 Factory 클래스
      - `MyPageViewModel` : LiveData를 생성하여 마이 페이지 관련 데이터 및 뷰 업데이트를 관리하는 ViewModel.
    - `search/`
      - `SearchViewModel` : LiveData를 생성하여 검색 화면 관련 데이터 및 뷰 업데이트를 관리하는 ViewModel.
      - `SearchViewModelFactory` : SearchViewModel 생성을 위한 Factory 클래스
    - `MainSharedViewModel` : 프래그먼트 간 데이터를 공유하고 관리하기 위한 ViewModel
- `util`
  - `Util` : 여러 유틸리티 기능을 제공하는 유틸리티 클래스
- `DisneyMinusApp` : 

## Showcase

<img width="30%" alt="image" src="https://github.com/Disneym/Disneym/assets/133577209/b2f5e7b2-7fab-49c1-b4b0-662b4facd824"><img width="30%" alt="image" src="https://github.com/Disneym/Disneym/assets/133577209/f7640656-58a8-4562-b171-cc0864c337c4"><img width="30%" alt="image" src="https://github.com/Disneym/Disneym/assets/133577209/8631a7f8-199a-454a-aee2-c03e80d1a27d">

<img width="30%" alt="image" src="https://github.com/Disneym/Disneym/assets/133577209/80a8ee9f-5852-4040-aba9-79d874dd6015"><img width="30%" alt="imagee" src="https://github.com/Disneym/Disneym/assets/133577209/1d6a56fe-06ea-49b7-807f-b98d93b580b5"><img width="30%" alt="image" src="https://github.com/Disneym/Disneym/assets/133577209/46b91b34-3431-4d2f-aea2-c64fdb9928f9">

<img width="30%" alt="image" src= "https://github.com/Disneym/Disneym/assets/132810978/158f24b9-ee67-4a98-a97c-0205052c0f3e"><img width="30%" alt="image" src="https://github.com/Disneym/Disneym/assets/133577209/cd6e9fd0-5078-47cd-b2e8-63dc62df639d"><img width="30%" alt="image" src="https://github.com/Disneym/Disneym/assets/133577209/a4149bc4-0574-4d73-a400-6e003ab62832">


<img width="30%" alt="image" src="https://github.com/Disneym/Disneym/assets/132810978/248da3fd-d1ea-4c9c-a605-b36023818168"><img width="30%" alt="image" src="https://github.com/Disneym/Disneym/assets/132810978/b2093df4-ad27-4899-9520-0fd60f770968"><img width="30%" alt="image" src="https://github.com/Disneym/Disneym/assets/132810978/6f096690-ae44-4a91-88d6-b0db43458578">

<img width="30%" alt="image" src="https://github.com/Disneym/Disneym/assets/132810978/99a9699a-ecb7-4611-a206-0d4abfad5024"><img width="30%" alt="image" src="https://github.com/Disneym/Disneym/assets/132810978/5c74092e-53d7-4d7a-82d0-df4bfc8211ed"><img width="30%" alt="image" src="https://github.com/Disneym/Disneym/assets/132810978/8af1d102-eac4-4a55-be86-39c468aa7f09"> 



## Team Notion 
[보러가기](https://grave-leotard-2bf.notion.site/Disney-Team-Project-9c3b5ce16e8d4b4d87b810261a0a63e3?pvs=4)
