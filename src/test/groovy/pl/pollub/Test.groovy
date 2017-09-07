/*
 * Copyright (c) Asseco Business Solutions S.A. All rights reserved.
 */

package pl.pollub

import spock.lang.Specification

class Test extends Specification {

    def "HashMap accepts null key"() {
        given: "2 and 3"
        def a = 2
        def b = 3

        when: "we sum it"
        def sum = a + b

        then: "we got 5"
        sum == 5
    }

    def "Test uniqueness with size"() {
        given:
        def col = Arrays.asList(1,2,2,3,4,5);

        expect:
        col.size() == 5
    }

}
