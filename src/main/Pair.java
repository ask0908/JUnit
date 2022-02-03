package main;

/* 해시테이블의 key로 환율 2개 저장 시 사용하는 클래스 */
public class Pair {
    // IDE는 final을 붙이는 걸 권장함
    private final String from;
    private final String to;

    public Pair(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        // 실제 equals() 구현과 비교해서 뭐가 빠졌는가?
        Pair pair = (Pair) o;
        return from.equals(pair.from) && to.equals(pair.to);
    }

    // 이 메서드는 정의와 사용법부터 보고 가야 함. 저자는 0을 리턴하는 건 최악이라고 했다
    @Override
    public int hashCode() {
        return 0;
    }
}
