<div align="center">
  
  ![header](https://capsule-render.vercel.app/api?type=Waving&height=200&text=week3_mini_assignment&fontColor=d5e6f5&color=timeGradient&animation=fadeIn)
  
</div>

# 3주차 과제

### **연락처 리스트 앱 만들기 (RecyclerView 활용)**

1. **레이아웃 요구사항**
    - 메인 화면에는 RecyclerView 하나만 표시합니다.
    - 연락처 아이템 레이아웃에는 사진, 이름, 전화번호를 표시하는 뷰가 포함되어야 합니다.

<br>

2. **데이터 모델 요구사항**
    - 연락처 클래스: 사진, 이름, 전화번호, 즐겨찾는여부 필드를 포함합니다.

<br>

3. **더미 데이터 요구사항**
    - 10명 이상의 연락처 더미 데이터를 생성합니다. 각 연락처는 사진, 이름, 전화번호, 즐겨찾기여부를 포함해야 합니다.

<br>

4. **어댑터 요구사항**
    - 어댑터 클래스: RecyclerView.Adapter를 확장합니다.
    - 연락처 데이터를 바인딩하는 로직이 포함되어야 합니다. → ViewBinding 활용 해보기
    - 데이터모델의 즐겨찾는 여부에 따라 viewType을 나눠 표시해보세요 (즐겨찾기) ViewType은 총 두개입니다.

<br>
    
5. **RecyclerView 설정 요구사항**
    - 메인 활동에서 RecyclerView를 어댑터와 연결하고, 더미 데이터를 로드하여 표시합니다.
    - 추가 선택 과제
        - 실제 폰에 있는 연락처를 불러옵니다. (READ_CONTACTS 권한이 필요합니다.)

<br>
    
6. **아이템 클릭 이벤트 처리 요구사항**
    - 추가 선택 과제
        - 전화를 걸어 봅시다 (hint : tel://, runtime permission)
