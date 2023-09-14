![header](https://capsule-render.vercel.app/api?type=waving&color=0:6e45e2,100:88d3ce&height=290&section=header&text=[내배캠]%20후발대%206주차%20개인과제&fontColor=ffffff&fontSize=50&animation=fadeIn&fontAlignY=38&desc=with%20Sparta%20Coding%20Club)

## 🎉 Completed!

- [X]  **Activity Life cycle**
- [X]  **Fragment Life cycle**
- [X]  **확장 함수**

## 📂 Assignment

### Activity Life cycle

1. MainActivity가 있어야 합니다.
2. MainActivity에는 **`Activity`** 라이프사이클의 각 단계에 해당하는 메소드 (**`onCreate`**, **`onStart`**, **`onResume`**, **`onPause`**, **`onStop`**, **`onDestroy`**)를 오버라이드하고 각 메소드가 호출될 때 로그 메시지를 출력합니다.
3. 메인 액티비티에는 두 번째 액티비티를 시작하는 버튼이 있어야 합니다.
4. 두 번째 액티비티도 메인 액티비티와 동일한 라이프사이클 메소드를 오버라이드하고 로그 메시지를 출력합니다.

<br/>

### Fragment Life cycle

1. Activity에는 Fragment를 추가하거나 제거하는 버튼들이 있어야 합니다.
2. Fragment의 각 라이프사이클 메소드가 호출될 때마다 로그 메시지를 출력합니다.

**`onCreate`**

**`onCreateView`**

**`onViewCreated`**

**`onViewRestored`**

**`onStart`**

**`onResume`**

**`onPause`**

**`onStop`**

**`onSaveInstanceState`**

**`onDestroyView`**

**`onDestroy`**

모든 단계의 로그가 출력되는지 확인되면 완성입니다.
로그는 LogCat창을 열면 볼수 있습니다

<br/>

### 확장 함수

1. **`Context`** 클래스를 확장하여 **`showToast`**라는 메소드를 작성합니다.
2. 이 함수는 메시지(String)와 지속 시간(Int, 기본값은 **`Toast.LENGTH_SHORT`**) 두 가지 파라미터를 받습니다.
3. 함수를 호출하면 해당 메시지의 Toast가 지정된 지속 시간 동안 화면에 나타나야 합니다.
    1. 지속시간을 변경할수도 있습니다
4. MainActivity에서 이 확장 함수를 사용하여 토스트 메시지를 출력해봅니다.
    
    Ex : Hello World
