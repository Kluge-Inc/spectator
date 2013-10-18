package org.kluge

class ListUtils {

    static Integer parseMax( String max ) {

        if ( max?.isInteger() && max != '0' ) {
            def maxInteger = new Integer( max )
            return Math.min( maxInteger, 10 )
        }
        10

    }

    static Integer parseOffset( String offset ) {

        if ( offset?.isInteger() ) {
            return new Integer( offset )
        }
        null

    }

    static String parseOrder( String order ) {

        if ( order == 'asc' || order == 'desc' ) {
            return order
        }
        null

    }

    static String parseSort( sort, fields ) {
        fields.find { it == sort }
    }

}