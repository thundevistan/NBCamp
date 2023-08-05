1️⃣ **lv1. 로그인 페이지 만들기 (SignInActivity)**
---
- 디자인은 취향대로 해도 되나 화면 구성은 동일하게 해주세요. ✨
<img width="30%" src="https://user-images.githubusercontent.com/139092551/258107423-7f665f8f-3402-44e7-abe8-61508deaaebf.png"/>

- 새 프로젝트를 만들고 MainActivity의 이름을 SignInActivity로 바꿔주세요.
- 로고 이미지는 원하는 이미지로 넣어주세요.
- 아이디, 비밀번호를 입력받는 EditText를 넣어주세요. (미리보기 글씨 있어야함)
- 비밀번호 EditText는 입력 내용이 가려져야 합니다.
- 로그인 버튼을 누르면 HomeActivity가 실행됩니다. (Extra로 아이디를 넘겨줍니다.)
- 아이디/비밀번호 모두 입력 되어야만 로그인 버튼이 실행됩니다. (“로그인 성공”이라는  토스트 메세지 출력)
- 아이디/비밀번호 중 하나라도 비어 있다면 “아이디/비밀번호를 확인해주세요” 라는 토스트 메세지 출력
- 회원가입 버튼을 누르면 SignUpActivity가 실행됩니다.

<br/>


2️⃣ **lv2. 회원가입 페이지 만들기 (SignUpActivity)**
---
- 디자인은 취향대로 해도 되나 화면 구성은 동일하게 해주세요. ✨

<img width="30%" src="https://user-images.githubusercontent.com/139092551/258402734-219a4bb2-3c47-4fe2-aaa1-f274acbf5144.png"/>

- SignpActivity를 생성해 주세요.
- 타이틀 이미지는 원하는 이미지로 넣어주세요.
- 이름, 아이디, 비밀번호 모두 입력 되었을 때만 회원가입 버튼이 눌립니다.
- 셋 중 하나라도 비어있으면 “입력되지 않은 정보가 있습니다.” 라는 토스트 메세지 출력
- 비밀번호 EditText는 입력 내용이 가려져야 합니다.
- 회원가입 버튼이 눌리면 SignInActivity로 이동합니다. (finish 활용)

<br/>


3️⃣ **lv3. 자기소개 페이지 만들기 (HomeActivity)**
---
- 디자인 및 화면구성은 마음대로~~~ ✨

<img width="30%" src="https://user-images.githubusercontent.com/139092551/258402837-9b666d05-c0d4-4e54-807a-24108de2b503.png"/>

- HomeActivity를 생성해 주세요.
- SignInActivity에서 받은 extra data(아이디)를 화면에 표시해주세요.
- ImageView, TextView 외에 각종 Widget을 활용해 자유롭게 화면을 디자인 해주세요.
    - 이름, 나이, MBTI 등 자기소개등이 들어가는 위젯을 자유롭게 디자인해주세요.
- 종료 버튼이 눌리면 SignInActivity로 이동합니다. (finish 활용)

<br/>


⚙ **선택과제 : 필수는 아니에요~**
---
선택 과제는 안드로이드 앱개발 입문 강의를 기반으로 하지만 한 걸음 더 성장하기 위해 고민하며 공부한 후 구현하는 과제 입니다.  혼자, 또는 팀원과 함께 공부하며 도전해보세요!!

### 1. **화면 이동 + @**

- 회원 가입페이지에서 입력한 아이디/비밀번호가 로그인 화면으로 돌아올 때 자동 입력되는 기능!
- Hint! `registerForActivityResult` 를 알아봅시다.
- [참고 영상] (https://github.com/thundevistan/IntroduceAPP/issues/7#issue-1837803090)

### 2. 자기 소개 랜덤 사진

- 5장의 사진을 등록합니다. (drawable 폴더)
- 자기소개 페이지가 시작될 때 5장 중 랜덤으로 1장의 사진이 표시됩니다.
- [참고 영상] (https://github.com/thundevistan/IntroduceAPP/issues/7#issue-1837803090)

<br/>

💡 **도전과제**
---
### Custom button design.
1. HomeActivity의 종료 버튼을 아래 동영상 처럼 만들어주세요.
2. 동작방식
- 버튼 모양은 ractangle, 모서리가 10dp 둥글게 만들어요
- 종료 버튼을 누르면(pressed) 배경색과 스마일 아이콘, 종료 텍스트 색상이 변경됩니다.
- 스마일 아이콘 및 텍스트 색상은 임의 변경 가능합니다.
- 참고로, HomeActivity.kt파일은 건들지 않습니다.  (selector이용)
- [참고 영상] (https://github.com/thundevistan/IntroduceAPP/issues/8#issue-1837821223)
