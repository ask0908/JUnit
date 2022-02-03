import main.Bank;
import main.Expression;
import main.Money;
import main.Sum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DollarTest {
    // 8장 - 객체 만들기
    @Test
    public void testMultiplication() {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
    }

    // 6장 - 돌아온 '모두를 위한 평등', 7장 - 사과와 오렌지, 8장 - 객체 만들기
    // 11장 - 모든 악의 근원
    @Test
    public void testEquality() {
        /* 주석 부분은 JUnit 5.x 기준으로 주석 아래의 코드로 변환하는 걸 IDE에서 권장한다 */
//        assertTrue(Money.dollar(5).equals(Money.dollar(5)));
        assertEquals(Money.dollar(5), Money.dollar(5));
//        assertFalse(Money.dollar(5).equals(Money.dollar(6)));
        assertNotEquals(Money.dollar(5), Money.dollar(6));
//        assertFalse(Money.franc(5).equals(Money.dollar(5)));
        assertNotEquals(Money.franc(5), Money.dollar(5));
    }

    // 9장 - 우리가 사는 시간
    @Test
    public void testCurrency() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }

    // 12장 - 드디어, 더하기
    @Test
    public void testSimpleAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");    // 은행에서 USD 환율 적용받음
        assertEquals(Money.dollar(10), reduced);
    }

    // 13장 - 진짜로 만들기
    @Test
    public void testPlusReturnSum() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five, sum.augend);
        assertEquals(five, sum.addend);
    }

    // 13장 - 진짜로 만들기
    @Test
    public void testReduceSum() {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(7), result);
    }

    // 13장 - 진짜로 만들기
    @Test
    public void testReduceMoney() {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1), "USD");
        assertEquals(Money.dollar(1), result);
    }

    // 14장 - 바꾸기
    @Test
    public void testReduceMoneyDifferentCurrency() {
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(Money.franc(2), "USD");
        assertEquals(Money.dollar(1), result);
    }

    // 14장 - 바꾸기
    @Test
    public void testArrayEquals() {
        assertArrayEquals(new Object[] {"abc"}, new Object[] {"abc"});
    }

    // 14장 - 바꾸기
    @Test
    public void testIdentityRate() {
        assertEquals(1, new Bank().rate("USD", "USD"));
    }

    // 15장 - 서로 다른 통화 더하기
    @Test
    public void testMixedAddition() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
        assertEquals(Money.dollar(10), result);
    }

    // 16장 - 드디어, 추상화
    @Test
    public void testSumPlusMoney() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(15), result);
    }

    // 테스트가 코드보다 긴 경우 이걸 고치는 방법은 책 29장(픽스처) 참고
    @Test
    public void testSumTimes() {
        Expression fiveBucks = Money.dollar(5); // 5USD
        Expression tenFrancs = Money.franc(10); // 10CHF
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Expression sum = new Sum(fiveBucks, tenFrancs).times(2);
        Money result = bank.reduce(sum, "USD");
//        System.out.println("result : " + result); // 20USD
        assertEquals(Money.dollar(20), result);
    }

}
