'use strict';

/* jasmine specs for controllers go here */

describe('Testing IndexCtrl', function(){
  var indexCtrl, scope;


  beforeEach(inject(function($controller, $rootScope){
    scope = $rootScope.$new();
    indexCtrl = $controller( 'IndexCtrl', {$scope: scope});
  }));


  it('should exist',  function() {
    //spec body
    expect(!! indexCtrl).toBe(true);
  });

  it('should have a parameter name where value is toto',  function() {
    //spec body
    expect(scope.name).toEqual('toto');
  });


});


describe('ContactsCtrl', function(){
  var contactsCtrl;


  beforeEach(function(){
    contactsCtrl = new ContactsCtrl();
  });


  it('should ....', function() {
    //spec body
  });
});
