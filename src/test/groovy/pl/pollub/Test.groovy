/*
 * Copyright (c) Asseco Business Solutions S.A. All rights reserved.
 */

package pl.pollub

import spock.lang.Specification

class Test extends Specification {

    def "HashMap accepts null key"() {
        setup:
        def map = new HashMap()
        map.put(null, "elem")
    }

}
