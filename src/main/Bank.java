package main;

import java.util.Hashtable;

public class Bank {

    // 해시테이블에 환율 저장
    private Hashtable<Pair, Integer> rates = new Hashtable<>();

    // 환율 계산
    public Money reduce(Expression source, String to) {
        /* 지저분한 코드. 왜?
        * 1. 캐스팅(형변환). 이 코드는 모든 Expression에 대해 작동해야 함
        * 2. public field와 그 field들에 대한 2단계 레퍼런스 */
//        Sum sum = (Sum) source;
//        int amount = sum.augend.amount + sum.addend.amount;
//        return new Money(amount, to);
        return source.reduce(this, to);
    }

    // 환율 계산
    public int rate(String from, String to) {
        if (from.equals(to)) return 1;  // USD -> USD로의 환율 요청 시 1이 돼야 함. testIdentityRate() 참고
        return rates.get(new Pair(from, to));
    }

    // 해시테이블에 저장할 환율 설정
    public void addRate(String from, String to, int rate) {
        rates.put(new Pair(from, to), rate);
//        System.out.println(rates.get(new Pair(from, to)));
    }

}
