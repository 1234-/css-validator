//
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang 2015.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2015/WD-css-inline-3-20150917/#propdef-initial-letter-wrap
 */
public class CssInitialLetterWrap extends org.w3c.css.properties.css.CssInitialLetterWrap {

	public static final CssIdent[] allowed_values;

	static {
		String[] _allowed_values = {"none", "first", "all", "grid"};
		int i = 0;
		allowed_values = new CssIdent[_allowed_values.length];
		for (String s : _allowed_values) {
			allowed_values[i++] = CssIdent.getIdent(s);
		}
	}

	public static final CssIdent getAllowedIdent(CssIdent ident) {
		for (CssIdent id : allowed_values) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	/**
	 * Create a new CssInitialLetterWrap
	 */
	public CssInitialLetterWrap() {
		value = initial;
	}

	/**
	 * Set the value of the property
	 *
	 * @param expression The expression for this property
	 * @param check      set it to true to check the number of values
	 * @throws org.w3c.css.util.InvalidParamException
	 *          The expression is incorrect
	 */
	public CssInitialLetterWrap(ApplContext ac, CssExpression expression,
								boolean check) throws InvalidParamException {

		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}

		setByUser();
		CssValue val = expression.getValue();

		switch (val.getType()) {
			case CssTypes.CSS_NUMBER:
				// check that the number is a length (ie: 0)
				val.getLength();
			case CssTypes.CSS_LENGTH:
			case CssTypes.CSS_PERCENTAGE:
				value = val;
				break;
			case CssTypes.CSS_IDENT:
				CssIdent id = (CssIdent) val;
				if (inherit.equals(id)) {
					value = inherit;
					break;
				}
				value = getAllowedIdent(id);
				if (value != null) {
					break;
				}
			default:
				throw new InvalidParamException("value", val,
						getPropertyName(), ac);
		}
		expression.next();
	}

	public CssInitialLetterWrap(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}


}

