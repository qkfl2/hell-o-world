
1, 2 챕터에서 설명된 지식만을 이용해서 아래 코드를 어떻게든 함수형 스타일로 바꿔봅시다.

거꾸로 읽어도 같은 단어인지 확인하는 코드입니다.
wow, level, I.O.I 같은 단어를 찾는것이지요.

java-7까지는 이런식으로 구현했습니다.
이게 함수형 스타일로 잘 될지는 모르겠는데, 삽질을 해보는 것에 의의를 두겠습니다.

```java
boolean isTextPalindrome(@NonNull String text) {
    int left = 0;
    int right = text.length() - 1;
    while (left < right) {
        if (text.charAt(left++) != text.charAt(right--)) {
            return false;
        }
    }
    return true;
}
```

{본인 이름}.java로 올려주세요.
