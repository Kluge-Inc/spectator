package org.kluge

class VersionController {

    static allowedMethods = [
        index:'GET',
        content:'GET',
        list:'GET',
        create:'GET',
        save:'POST',
        edit:'GET',
        update:'POST',
        delete:'POST'
    ]

    def versionService
    def crackingService

    def index() {
        redirect( action:'content', params:params )
    }

    def content() {
        renderList( 'content' )
    }

    def list() {
        renderList( 'list' )
    }

    def create() {

        def model = [ versionInstance:new Version( params ) ]
        render( template:'form', model:model )

    }

    def save() {

        def version = new Version( params )
        saveOnDb( version, 'create',
            'version.created.message' )

    }

    def edit( Long id ) {

        def map = get( id )
        if ( !map ) return
        map.edit = true
        render( template:'form', model:map )

    }

    def update( Long id ) {

        def map = get( id )
        if ( !map ) return
        map.versionInstance.properties = params
        map.edit = true
        saveOnDb( map.versionInstance, 'update',
            'version.updated.message', true )

    }

    def delete( Long id ) {

        def map = get( id )
        if ( !map ) return
        versionService.delete( map.versionInstance )
        flash.listMessage = message( code:'default.deleted.message',
            args:[ message( code:'version.label',
            default:'Version' ), id ] )
        redirect( action:'content' )

    }

    private void renderList( template ) {

        def model = [:]
        def result = versionService.list( params )
        model.items = result.items
        model.total = result.total
        render( template:template, model:model )

    }

    private Map get( Long id ) {

        if ( id == null ) {
            notifyCrack()
            return null
        }
        def version = versionService.get( id )
        if ( !version ) {
            notifyCrack()
            return null
        }
        [ versionInstance:version ]

    }

    private void saveOnDb( version, method, msg, edit = false ) {

        try {
            versionService."${method}"( version )
        } catch ( IllegalArgumentException e ) {
            response.status = 400
            render( template:'form', model:[ versionInstance:version,
                edit:edit ] )
            return
        }
        flash.formMessage = message(
            code:"default.${edit?'updated':'created'}.message",
            args:[ message( code:'version.label',
            default:'Version' ), version.id])
        redirect( action:'edit', id:version.id )

    }

    private void notifyCrack() {

        crackingService.notify( request, params )
        redirect( controller:'logout' )

    }

}