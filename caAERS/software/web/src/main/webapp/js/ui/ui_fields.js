/**
 * This file contains function that helps in
 *   - showing and hiding fields
 *   - showing and hiding rows
 *   - making fields mandatory
 *
 * Biju Joseph
 */

/*
* This will show the entire row by its id
*
* */
function showRow(_rowId){
    if($(_rowId)) $(_rowId).show();
}

/*
* This will hide the entire row by its id
* 
* */
function hideRow(_rowId){
   if($(_rowId)) $(_rowId).hide();
}

/*
* This will hide the entire row with its children, label and value
*
* */
function hideFieldAndRow(_fieldPath){
   if($(_fieldPath)) $(_fieldPath).clear();
   hideRow(_fieldPath + "-row")
}

//will make the field and its row appear
function showFieldAndRow(_fieldPath){
    showRow(_fieldPath + "-row");
}

//will make the field mandatory by putting the mandatory class
function makeFieldMandatory(_fieldPath){

  var _row = $(_fieldPath + "-row");

  //add asterisk to the label
  if(_row){
    _row.select('div.label').each(function(_lbl){
        if(!_lbl.hasClassName('mandatory')){
            _lbl.addClassName('mandatory');
            _lbl.insert({top : '<span class="required-indicator">*</span>'});
        }
    });

    _row.select('div.value').each(function(_lbl){
        if(!_lbl.hasClassName('mandatory')){
            _lbl.addClassName('mandatory');
        }
    });

  }

  //add required class to the field.
  var _fld = $(_fieldPath);
  if(_fld){
    _fld.addClassName('required');

  }
    
}

//will make the field optional by removing the validation-required, mandatory flag
function makeFieldOptional(_fieldPath){

    var _row = $(_fieldPath + "-row");

    //remove asterisk to the label
    if(_row){
      _row.select('div.label').each(function(_lbl){
          _lbl.removeClassName('required') ;
          _lbl.removeClassName('mandatory') ;
          //remove the span
          _lbl.select('span.required-indicator').each(function(n){ n.remove(); });
      });

      _row.select('div.value').each(function(_lbl){
          _lbl.removeClassName('mandatory') ;
      });

    }

    //remove required class to the field.
    var _fld = $(_fieldPath);
    if(_fld){
      _fld.removeClassName('required');
      var _vClass = _fld.classNames().find(function(n){
         if(n.indexOf('validate-') >= 0) {
              return n;
         }
      });

      if(_vClass != null && _vClass.length > 15){
          _fld.removeClassName(_vClass);
          _vClass = _vClass.sub('&&NOTEMPTY','').sub('NOTEMPTY&&','');
          _fld.addClassName(_vClass);
      }

    }

}


//will make the field required by putting the validation-reqired flag
function makeFieldRequired(_fieldPath){

  var _row = $(_fieldPath + "-row");

  //add asterisk to the label
  if(_row){
    _row.select('div.label').each(function(_lbl){
        if(!_lbl.hasClassName('required')){
            _lbl.addClassName('required');
            _lbl.insert({top : '<span class="required-indicator">*</span>'});
        }
    });
  }

  //add required class to the field.
  var _fld = $(_fieldPath);
  if(_fld){
    _fld.addClassName('required');
    var _vClass = _fld.classNames().find(function(n){
       if(n.indexOf('validate-') >= 0) {
            return n;
       }
    });

    if(_vClass == null) {
       _fld.addClassName('validate-NOTEMPTY');
    }else if(_vClass.indexOf('NOTEMPTY') < 0){
      _fld.removeClassName(_vClass);
      _fld.addClassName('validate-NOTEMPTY' + _vClass.sub("validate-","&&"));
    }
  }

}
