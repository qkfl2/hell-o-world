# Hell-o-world Java-8 세미나 문제풀이

### 1,2 챕터 문제
1, 2 챕터에서 설명된 지식만을 이용해서 아래 코드를 어떻게든 함수형 스타일로 바꿔봅시다.
꼭 아래 알고리즘에서 변형할 필요는 없고, 다른 알고리즘으로 구현해도 상관없습니다.

거꾸로 읽어도 같은 단어인지 확인하는 코드입니다.
wow, level, AOA, I.O.I 같은 단어를 찾는것이지요.

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

### 3 챕터 문제
주어진 샘플 파일(1.txt, 2.txt, 3.txt)에 있는 이메일 정보를 통해 각 도메인별 몇명의 사용자가 이용중인지를 출력 하시오.   
flatmap 도 이용하면 좋을 것 같습니다.(파일을 나눠 보았습니다.)

### 4 챕 문
to be continued...
---
결과는 {본인 이름}.java로 올려주세요.
