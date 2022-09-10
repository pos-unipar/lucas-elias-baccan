// Copyright 2016 Documize Inc. <legal@documize.com>. All rights reserved.
//
// This software (Documize Community Edition) is licensed under
// GNU AGPL v3 http://www.gnu.org/licenses/agpl-3.0.en.html
//
// You can operate outside the AGPL restrictions by purchasing
// Documize Enterprise Edition and obtaining a commercial license
// by contacting <sales@documize.com>.
//
// https://documize.com

import { helper } from '@ember/component/helper';

export function isOneOf(params/*, hash*/) {
	if (!_.isUndefined(params) || !_.isNull(params)) {
		if (params.length >= 2) {
			let value = params[0];
			for (let i=1; i < params.length; i++) {
				if (params[i] == value) return true;
			}
		}
	}
	
	return false;
}

export default helper(isOneOf);
