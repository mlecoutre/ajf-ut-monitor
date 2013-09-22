'use strict';

/* http://docs.angularjs.org/guide/dev_guide.e2e-testing */

describe('End to End Blank app', function() {

  beforeEach(function() {
    browser().navigateTo('../../main/webapp/index.html');
  });


  it('should automatically redirect to /index when location hash/fragment is empty', function() {
    expect(browser().location().url()).toBe("/index");
  });


  describe('index', function() {

    beforeEach(function() {
      browser().navigateTo('#/index');
    });


    it('should render view1 when user navigates to /index', function() {
      expect(element('[ng-view] h1:first').text()).
        toMatch(/Hello, world!/);
    });

  });


  describe('contacts', function() {

    beforeEach(function() {
      browser().navigateTo('#/contacts');
    });


    it('should render contacts when user navigates to /contacts', function() {
      expect(element('[ng-view] p:first').text()).
        toMatch(/List of contacts/);
    });

  });
});
