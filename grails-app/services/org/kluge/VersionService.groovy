package org.kluge

import grails.gorm.DetachedCriteria
import grails.validation.ValidationException

class VersionService {

    Map list( Map params ) {

        processParams( params )
        def criteria = new DetachedCriteria( Version ).build {}
        [ items:criteria.list( params ), total:criteria.count() ]

    }

    void create( Version version ) {
        save( version )
    }

    void update( Version version ) {
        save( version )
    }

    Version get( Long id ) {

        if ( id == null ) throw new IllegalArgumentException(
            "Parameter 'id' is null" )
        Version.findById( id )

    }

    void delete( Version version ) {

        if ( version == null ) throw new IllegalArgumentException(
            "Parameter 'version' is null" )
        version.delete()

    }

    private void processParams( params ) {

        params.max = ListUtils.parseMax( params.max )
        params.offset = ListUtils.parseOffset( params.offset )
        params.order = ListUtils.parseOrder( params.order )
        def fields = [ 'name', 'date', 'user', 'id' ]
        params.sort = ListUtils.parseSort( params.sort, fields )

    }

    private void save( Version version ) {

        if ( !version ) throw new IllegalArgumentException(
            "Parameter 'version' is null" )
        try {
            version.save( failOnError:true )
        } catch ( ValidationException ) {
            throw new IllegalArgumentException(
                "Parameter 'version' is invalid" )
        }

    }

}