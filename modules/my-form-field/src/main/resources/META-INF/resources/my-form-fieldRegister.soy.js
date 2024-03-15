/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from my-form-fieldRegister.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace MyFormFieldRegister.
 * @hassoydeltemplate {PageRenderer.RegisterFieldType.idom}
 * @public
 */

goog.module('MyFormFieldRegister.incrementaldom');

goog.require('soy');
var soyIdom = goog.require('soy.idom');

var $templateAlias1 = Soy.getTemplate('MyFormField.incrementaldom', 'render');


/**
 * @param {Object<string, *>=} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes}
 */
function __deltemplate_s109_d4400c38(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  opt_data = opt_data || {};
  $templateAlias1(opt_data, null, opt_ijData);
}
exports.__deltemplate_s109_d4400c38 = __deltemplate_s109_d4400c38;
if (goog.DEBUG) {
  __deltemplate_s109_d4400c38.soyTemplateName = 'MyFormFieldRegister.__deltemplate_s109_d4400c38';
}
soy.$$registerDelegateFn(soy.$$getDelTemplateId('PageRenderer.RegisterFieldType.idom'), 'myFormField', 0, __deltemplate_s109_d4400c38);

templates = exports;
return exports;

});

export { templates };
export default templates;
/* jshint ignore:end */
