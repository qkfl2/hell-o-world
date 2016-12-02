1949년 인도 수학자 Kaprekar는 Kaprekar 연산을 고안해냈다. Kaprekar 연산은 네 자리 수 중 모든 자리수가 같지 않은 수(1111, 2222 등을 제외한)의 각 자리 의 숫자를 재배열해서 만들 수 있는 가장 큰 수와 가장 작은 수를 만들어서 그 차이를 계산하는데, 그 결과로 나온 새로운 숫자를 갖고 같은 과정을 반복하는 것이다.
간단한 연산이지만 Kaprekar는 이 연산이 놀라운 결과를 보여준다는 것을 발견 했다. 올해 연도인 2008로 그 결과를 알아보자. 2008로 만들 수 있는 가장 큰 수는 8200이고 가장 작은 수는 0028이다.

8200 – 0028 = 8172
8721 – 1278 = 7443
7443 – 3447 = 3996
9963 – 3699 = 6264
6642 – 2466 = 4176
7641 – 1467 = 6174
7641 – 1467 = 6174

6174에 도달한 다음에는 매번 6174를 만들어 낸다. 2008만이 유독 6174에 도달하는 것이 아니라 한 숫자로 이루어지지 않은 모든 네 자리 수는 Kaprekar 연산 을 통해 6174로 가게 된다. 2008의 경우 6 단계를 거쳐 6174로 가게 되었는데, 다른 숫자가 입력으로 주어졌을 때 몇 단계 만에 6174로 가는지 알아내는 프로 그램을 작성하시오.

입력
	1000<= N <= 9999 의 int 스트림을 선언하고, Kaprekar수 6174에 해당하는 count중에 가장 큰 count값을 출력한다.
	단, 같은수 1111, 2222등과 같은 숫자는 필터를 통해 제외한다.

참고
  https://ko.wikipedia.org/wiki/카프리카_상수