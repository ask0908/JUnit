package main;

/* Dollar, Franc 클래스가 상속하는 부모 클래스 */
public class Money implements Expression {
    protected int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    /* 입력받은 숫자를 해당 환율로 설정하는 메서드들 */
    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    // 곱하는 메서드. testSumTimes()에서 사용된 times()의 작동 흐름은 생각해보기
    public Expression times(int multiplier) {
        return new Money(amount * multiplier, currency);
    }

    public String currency() {
        return currency;
    }

    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    // Dollar에서 이 클래스로 옮김
    @Override
    public boolean equals(Object obj) {
        Money money = (Money) obj;
        return amount == money.amount && currency().equals(money.currency());
    }

    // 이 메서드는 디버그 출력에만 사용된다. 모든 소스코드 중에서 이 메서드가 쓰이는 곳은 없음
    // 왜 equals(), toString()을 구현해야 하는가?
    @Override
    public String toString() {
        return amount + " " + currency;
    }

    @Override
    public Money reduce(Bank bank, String to) {
        // testSumTimes() 기준 2가 리턴된다
        int rate = bank.rate(currency, to);
        System.out.println("rate : " + rate);
        return new Money(amount / rate, to);
    }
}
