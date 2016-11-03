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
주어진 샘플 파일(1.txt, 2.txt, 3.txt)에 있는 이메일 정보를 통해  
각 도메인별 몇명의 사용자가 이용중인지를 출력 하시오.    
flatmap 도 이용하면 좋을 것 같습니다.(파일을 나눠 보았습니다.)  

### 4 챕터 문제
각종 재료를 첨가하여 원하는 비빔밥을 만드려고 합니다.
아래의 코드 예시처럼 원하는 재료들을 추가해서 비비면 
비빔밥이 완성되도록 데코레이터 패턴을 이용하여 출력해 봅시다.

Bibimbap bibimbap = new Bibimbap();
bibimbap.addMaterial(재료::콩나물, 재료::고추장);
System.out.println(bibimbap.mix());

완성된 비빔밥에 대한 출력은 다음과 같이 나열해 봅니다.
"재료명1 재료명2 ... 비빔밥"

콩나물, 고추장 추가
> 콩나물 고추장 비빔밥

참지, 마요네즈, 스팸 추가
> 참치 마요네즈 스팸 비빔밥


### 5 챕터 문제
to be continued...
---
결과는 {본인 이름}.java로 올려주세요.
