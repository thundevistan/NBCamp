<div align="center">
  
  ![header](https://capsule-render.vercel.app/api?type=Waving&height=200&text=week7_assignment&fontColor=d5e6f5&color=timeGradient&animation=fadeIn)
  
</div>

💡 Goal : 사과마켓 앱 만들기 (feat. 당근마켓)

<p align="center"><img
src="https://github.com/thundevistan/week7_assignment/assets/139092551/4cee7077-9587-45da-ae22-4178c1cc9f36" width="30%" height="30%"></p>

---

⚙ **필수과제 :  반드시 구현해야 할 기능이에요.**
1. 사과마켓 메인 페이지 만들기
2. 판매상품 상세 페이지 만들기

<br>

1️⃣ 메인 페이지 만들기

<p align="center"><img
src="https://github.com/thundevistan/week7_assignment/assets/139092551/2bee64f1-59f5-499e-b734-b514f3e2097e" width="30%" height="30%"></p>

<br>

- 디자인 및 화면 구성을 최대한 동일하게 해주세요. (사이즈 및 여백도 최대한 맞춰주세요.) ✨
- 상품 데이터는 아래 dummy data 를 사용합니다. (더미 데이터는 자유롭게 추가 및 수정 가능)
- 데미데이터 : [이미지 링크](https://drive.google.com/file/d/1P5AnZI1N2AB7yNqwkgF-KxlUdDjkmrBu/view?usp=sharing),  [상품 리스트 링크](https://docs.google.com/spreadsheets/d/1m9VDxJ3Q7dLEjefnWBq4fCghtWIUFnpM/edit?usp=sharing&ouid=116688204055896164464&rtpof=true&sd=true)  (←링크 권한 없으면 [여기](https://drive.google.com/drive/folders/1ZYQIxmP8JAXpcxvQB3QekYZLYQiNlZqK?usp=sharing) 클릭)
- RecyclerViewer를 이용해 리스트 화면을 만들어주세요.
- 상단 툴바를 제거하고 풀스크린 화면으로 세팅해주세요. (statusbar는 남기고)
- 상품 이미지는 모서리를 라운드 처리해주세요.
- 상품 이름은 최대 두 줄이고, 그래도 넘어가면 뒷 부분에 …으로 처리해주세요.
- 뒤로가기(BACK)버튼 클릭시 종료하시겠습니까? [확인][취소] 다이얼로그를 띄워주세요. (예시 비디오 참고)
- 상단 종모양 아이콘을 누르면 Notification을 생성해 주세요. (예시 비디오 참고)
- 상품 가격은 1000단위로 콤마(,) 처리해주세요.
- 상품 아이템들 사이에 회색 라인을 추가해서 구분해주세요.
- 상품 선택시 아래 상품 상세 페이지로 이동합니다.
- 상품 상세페이지 이동시 intent로 객체를 전달합니다. (Parcelize 사용)

<br>

2️⃣ 상품 상세 페이지 만들기

<p align="center"><img
src="https://github.com/thundevistan/week7_assignment/assets/139092551/546cbfe9-dd36-424b-b356-43d69227817a" width="30%" height="30%"></p>

<br>

- 디자인 및 화면 구성을 최대한 동일하게 해주세요. (사이즈 및 여백도 최대한 맞춰주세요.) ✨
- 메인화면에서 전달받은 데이터로 판매자, 주소, 아이템, 글내용, 가격등을 화면에 표시합니다.
- 하단 가격표시 레이아웃을 제외하고 전체화면은 스크롤이 되어야합니다. (예시 비디오 참고)
- 상단 < 버튼을 누르면 상세 화면은 종료되고 메인화면으로 돌아갑니다.

---


⚙ **선택과제 : 필수는 아니에요~**

선택 과제는 안드로이드 앱개발 입문 강의를 기반으로 하지만 한 걸음 더 성장하기 위해 고민하며 공부한 후 구현하는 과제 입니다.  혼자, 또는 팀원과 함께 공부하며 도전해보세요!!

<br>

### 1. 스크롤 상단 이동!

- 스크롤을 최상단으로 이동시키는 플로팅 버튼 기능 추가
- 플로팅 버튼은 스크롤을 아래로 내릴 때 나타나며, 스크롤이 최상단일때 사라집니다.
- 플로팅 버튼을 누르면 스크롤을 최상단으로 이동시킵니다.
- 플로팅 버튼은 나타나고 사라질때 fade 효과가 있습니다.
- 플로팅 버튼을 클릭하면(pressed) 아이콘 색이 변경됩니다.
- 참고 영상

- 

### 2. 상품 삭제하기!

- 상품을 롱클릭 했을때 삭제 여부를 묻는 다이얼로그를 띄우고
- 확인을 선택시 해당 항목을 삭제하고 리스트를 업데이트한다.
- 해당 상품이 삭제되었는지 확인!!
- 참고 영상

- ### *3. [찐도전과제] 좋아요 처리!!  ← 많은 고민이 필요합니다.*

- 상품 상세 화면에서 좋아요 선택시 아이콘 변경 및 Snackbar 메세지 표시
- 메인 화면으로 돌아오면 해당 상품에 좋아요 표시 및 좋아요 카운트 +1
- 상세 화면에서 좋아요 해제시 이전 상태로 되돌림
- 참고 영상
