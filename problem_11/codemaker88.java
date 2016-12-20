package ChapterEX;

/**
 * Created by codemaker88 on 2016-12-20.
 */

public class Chapter11 {

    public void solve() {
        /*
        Ex)
        0 : 시민
        1 : 무법자
        2 : 정신병자

        1 0 1 1 0 0 2 0 0
        결과 : 시민 승리 3
         */
        사람 하나 = new 무법자();
        사람 둘 = new 시민();
        사람 셋 = new 무법자();
        사람 넷 = new 무법자();
        사람 다섯 = new 시민();
        사람 여섯 = new 시민();
        사람 일곱 = new 정신병자();
        사람 여덟 = new 시민();
        사람 아홉 = new 시민();

        하나.set왼쪽(아홉);
        하나.set오른쪽(둘);
        둘.set왼쪽(하나);
        둘.set오른쪽(셋);
        셋.set왼쪽(둘);
        셋.set오른쪽(넷);
        넷.set왼쪽(셋);
        넷.set오른쪽(다섯);
        다섯.set왼쪽(넷);
        다섯.set오른쪽(여섯);
        여섯.set왼쪽(다섯);
        여섯.set오른쪽(일곱);
        일곱.set왼쪽(여섯);
        일곱.set오른쪽(여덟);
        여덟.set왼쪽(일곱);
        여덟.set오른쪽(아홉);
        아홉.set왼쪽(여덟);
        아홉.set오른쪽(하나);

        System.out.println(석양이진다빵빵빵(하나).invoke());
    }

    public TailCall<String> 석양이진다빵빵빵(final 사람 현재) {

        String 종료조건 = 종료조건확인(현재);
        if (종료조건 != null) {
            return TailCalls.done("결과 : " + 종료조건);
        } else {
            현재.행동();
            return TailCalls.call(() -> 석양이진다빵빵빵(현재.get오른쪽()));
        }
    }

    /*
        무법자가 모두 죽었을 경우. '시민 승리'와 살아남은 사람수를 출력하라.
        *시민과 정신병자만 남은 경우에도 시민승리이다.
        정신병자와 시민이 모두 죽었을 경우. '무법자 승리'와 살아남은 사람 수를 출력하라.
        정신병자 외에 모두 죽었을 경우. '정신병자 승리'를 출력하라.

        위 세 조건중 하나라도 만족할 경우 즉시 게임이 종료된다.

        Ex)
        0 : 시민
        1 : 무법자
        2 : 정신병자

        1 0 1 1 0 0 2 0 0

        1 0 1 1 0 0 2 0 0
        1(1) 0 1 1 0 0 2 0
        0(1) 1 1 0 0 2 0
        1(1) 1 0 0 2 0
        1(1) 1(1) 0 2 0
        1(1) 0(1) 2 0
        0(1) 2(1) 0
        결과 : 시민 승리 3
         */
    public String 종료조건확인(final 사람 현재) {
        String result = null;

        int 남은사람수 = 1;
        for (사람 인덱스 = 현재.get오른쪽(); 인덱스 != 현재; 남은사람수++, 인덱스 = 인덱스.get오른쪽()) {
        }
        //System.out.println("남은사람수 : " + 남은사람수);

        boolean temp = true;
        for (사람 인덱스 = 현재.get오른쪽(); 인덱스 != 현재; 인덱스 = 인덱스.get오른쪽()) {
            if (!(인덱스 instanceof 시민)) {
                temp = false;
                break;
            }
        }

        if (temp) {
            return "시민 승리 " + 남은사람수;
        } else {
            temp = true;
            for (사람 인덱스 = 현재.get오른쪽(); 인덱스 != 현재; 인덱스 = 인덱스.get오른쪽()) {
                if (!(인덱스 instanceof 무법자)) {
                    temp = false;
                    break;
                }
            }

            if (temp) {
                return "무법자 승리 " + 남은사람수;
            } else if (남은사람수 == 1 && 현재 instanceof 정신병자) {
                return "정신병자 승리";
            }
        }

        return result;
    }


    public abstract class 사람 {
        protected int 살인자카운트 = 0;
        protected 사람 왼쪽 = null;
        protected 사람 오른쪽 = null;

        public void set왼쪽(사람 왼쪽) {
            this.왼쪽 = 왼쪽;
        }

        public void set오른쪽(사람 오른쪽) {
            this.오른쪽 = 오른쪽;
        }

        public 사람 get왼쪽() {
            return this.왼쪽;
        }

        public 사람 get오른쪽() {
            return this.오른쪽;
        }

        public int get살인자카운트() {
            return this.살인자카운트;
        }

        public void 죽음() {
            this.왼쪽 = null;
            this.오른쪽 = null;
            //System.out.println(this);
        }

        public abstract void 빵야(사람 죽일놈);

        public abstract void 행동();
    }

    public class 시민 extends 사람 {

        @Override
        public void 행동() {
            if (this.왼쪽.get살인자카운트() > this.오른쪽.get살인자카운트()) {
                빵야(this.왼쪽);
            } else if (this.왼쪽.get살인자카운트() < this.오른쪽.get살인자카운트()) {
                빵야(this.오른쪽);
            } else {
                //아무것도 안함
            }
        }

        public void 빵야(사람 죽일놈) {
            사람 새사람 = null;
            if (죽일놈.get왼쪽() == this) {//자신의 오른쪽 쏨
                새사람 = 죽일놈.get오른쪽();
                this.오른쪽 = 새사람;
                새사람.set왼쪽(this);
                죽일놈.죽음();
            } else {
                새사람 = 죽일놈.get왼쪽();
                this.왼쪽 = 새사람;
                새사람.set오른쪽(this);
                죽일놈.죽음();
            }

            if (새사람 != null) {
                this.살인자카운트++;
            }
        }
    }

    public class 무법자 extends 사람 {

        @Override
        public void 행동() {
            if (!(this.왼쪽 instanceof 무법자 && this.오른쪽 instanceof 무법자)) {
                if (this.왼쪽 instanceof 시민) {
                    빵야(this.왼쪽);
                } else if (this.오른쪽 instanceof 시민) {
                    빵야(this.오른쪽);
                } else {
                    if (this.왼쪽.get살인자카운트() <= this.오른쪽.get살인자카운트()) {
                        빵야(this.왼쪽);
                    } else {
                        빵야(this.오른쪽);
                    }
                }
            }
        }

        public void 빵야(사람 죽일놈) {
            사람 새사람 = null;
            if (죽일놈.get왼쪽() == this) {//자신의 오른쪽 쏨
                새사람 = 죽일놈.get오른쪽();
                this.오른쪽 = 새사람;
                새사람.set왼쪽(this);
                죽일놈.죽음();
            } else {
                새사람 = 죽일놈.get왼쪽();
                this.왼쪽 = 새사람;
                새사람.set오른쪽(this);
                죽일놈.죽음();
            }

            if (새사람 != null) {
                this.살인자카운트++;
            }
        }
    }

    public class 정신병자 extends 시민 {

        @Override
        public void 행동() {
            int 남은사람수 = 1;
            for (사람 현재 = this.get오른쪽(); 현재 != this; 남은사람수++, 현재 = 현재.get오른쪽()) {

            }

            if (남은사람수 % 2 == 0) {
                사람 죽일놈 = this;
                for (int i = 0; i < 남은사람수 / 2; i++) {
                    죽일놈 = 죽일놈.get오른쪽();
                }

                빵야(죽일놈);
            } else {
                빵야(this);
            }
        }

        @Override
        public void 빵야(사람 죽일놈) {
            죽일놈.get왼쪽().set오른쪽(죽일놈.get오른쪽());
            죽일놈.get오른쪽().set왼쪽(죽일놈.get왼쪽());
            죽일놈.죽음();
        }
    }
}

