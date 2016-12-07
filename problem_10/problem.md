콜라츠 추측 (Collatz conjecture)은 1937년에 처음으로 이 추측을 제기한 로타르 콜라츠의 이름을 딴 것으로 3n+1 추측, 울람 추측, 혹은 헤일스톤(우박) 수열 등 여러 이름으로 불린다. 콜라츠 추측은 임의의 자연수가 다음 조작을 거쳐 항상 1이 된다는 추측이다.

짝수라면 2로 나눈다.
홀수라면 3을 곱하고 1을 더한다.
1이면 조작을 멈추고, 1이 아니면 첫 번째 단계로 돌아간다.
예를 들어, 6 에서 시작한다면, 차례로 6, 3, 10, 5, 16, 8, 4, 2, 1 이 된다.

참고 : https://ko.wikipedia.org/wiki/%EC%BD%9C%EB%9D%BC%EC%B8%A0_%EC%B6%94%EC%B8%A1

어떤 자연수에 대해서 몇번의 과정의 거쳐야 1이 되는지를 푸는 문제는 너무 쉽다.

따라서, 수금의 확률을 높이기 위해 새로운 개념을 도입하기로 한다.

어떤 자연수 n이 1이 될 때 까지 필요한 과정의 수를 depth라고 할 때,
depth가 같은 자연수 n들을 서로 친구인 수라고 정의하기로 한다.

* 예를 들어, 6의 depth는 9, depth 9에서의 친구는 2명이다.

문제 : 1 ~ 256까지의 depth중, 친구가 가장 많아질 때의 depth는 몇인가?
그 때, 서로 친구인 수들을 모두 출력하시오.