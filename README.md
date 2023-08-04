**lv1. 로그인 페이지 만들기 (SignInActivity)**
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

**lv2. 회원가입 페이지 만들기 (SignUpActivity)**
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

**lv3. 자기소개 페이지 만들기 (HomeActivity)**
---
- 디자인 및 화면구성은 마음대로~~~ ✨

<img width="30%" src="https://user-images.githubusercontent.com/139092551/258402837-9b666d05-c0d4-4e54-807a-24108de2b503.png"/>

- HomeActivity를 생성해 주세요.
- SignInActivity에서 받은 extra data(아이디)를 화면에 표시해주세요.
- ImageView, TextView 외에 각종 Widget을 활용해 자유롭게 화면을 디자인 해주세요.
    - 이름, 나이, MBTI 등 자기소개등이 들어가는 위젯을 자유롭게 디자인해주세요.
- 종료 버튼이 눌리면 SignInActivity로 이동합니다. (finish 활용)
