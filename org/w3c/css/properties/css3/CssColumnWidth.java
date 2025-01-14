// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
// Rewriten 2010 Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT 1995-2010  World Wide Web Consortium (MIT, ERCIM and Keio)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssCheckableValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2011/CR-css3-multicol-20110412/#column-width
 */

public class CssColumnWidth extends org.w3c.css.properties.css.CssColumnWidth {

	static CssIdent auto;

	static {
		auto = CssIdent.getIdent("auto");
	}

	/**
	 * Create a new CssColumnWidth
	 */
	public CssColumnWidth() {
		value = initial;
	}

	/**
	 * Create a new CssColumnWidth
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Incorrect value
	 */
	public CssColumnWidth(ApplContext ac, CssExpression expression,
						  boolean check) throws InvalidParamException {

		setByUser();
		CssValue val = expression.getValue();

		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}

		switch (val.getType()) {
			case CssTypes.CSS_NUMBER:
				val.getLength();
				// if we didn't fall in the first trap, there is another one :)
				throw new InvalidParamException("strictly-positive",
						expression.getValue(),
						getPropertyName(), ac);
			case CssTypes.CSS_LENGTH:
				CssCheckableValue l = val.getCheckableValue();
				l.checkStrictPositiveness(ac, this);
				value = val;
				break;
			case CssTypes.CSS_IDENT:
				if (inherit.equals(val)) {
					value = inherit;
					break;
				} else if (auto.equals(val)) {
					value = auto;
					break;
				}
			default:
				throw new InvalidParamException("value", expression.getValue(),
						getPropertyName(), ac);
		}
		expression.next();
	}

	public CssColumnWidth(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

	/**
	 * Is the value of this property a default value
	 * It is used by all macro for the function <code>print</code>
	 */
	public boolean isDefault() {
		return (auto == initial);
	}

}
