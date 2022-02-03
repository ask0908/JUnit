package main;

/* 클래스로 만들어도 되지만 인터페이스가 상대적으로 가볍다 */
public interface Expression {
    Money reduce(Bank bank, String to);
    Expression plus(Expression addend);
    Expression times(int multiplier);
}
