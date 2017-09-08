package pl.pollub

import spock.lang.Specification
import spock.lang.Unroll

class SampleSpockSpec extends Specification {

    @Unroll
    def "sum of #a and #b is #sum"() {

        expect: "sum of two numbers"
        sum == a + b

        where:
//        a << [1,2,3]
//        b << [3,2,1]
//        sum << [4,4,4]
        a | b | sum
        1 | 3 | 4
        2 | 2 | 4
        3 | 1 | 4

    }

    def "Test uniqueness with size"() {
        given:
        def col = [1, 2, 2, 3, 4, 5].toSet()

        expect:
        col.size() == 5
    }
//
//    def "searches tasks"() {
//
//
//        where:
//        tasks                                              | query   | results
//        ["zrób obiad", "kup mleko", "posprzątaj w kuchni"] | "obiad" | [1]
//        ["kup chleb", "kup mleko", "posprzątaj w kuchni"]  | "kup"   | [1, 2]
//        ["kup chleb", "kup mleko", "posprzątaj w kuchni"]  | "ku"    | [1, 2, 3]
//    }

}
