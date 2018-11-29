
package com.JavaUI.tablelayout;


abstract public class Value {
	/** Returns the value in the context of the specified table. */
	abstract public float get (Object table);

	/** Returns the value in the context of the specified cell. */
	abstract public float get (Cell cell);

	/** Returns the value in the context of a width for the specified table. */
	public float width (Object table) {
		return Toolkit.instance.width(get(table));
	}

	/** Returns the value in the context of a height for the specified table. */
	public float height (Object table) {
		return Toolkit.instance.height(get(table));
	}

	/** Returns the value in the context of a width for the specified cell. */
	public float width (Cell cell) {
		return Toolkit.instance.width(get(cell));
	}

	/** Returns the value in the context of a height for the specified cell. */
	public float height (Cell cell) {
		return Toolkit.instance.height(get(cell));
	}

	/** A value that is always zero. */
	static public final Value zero = new CellValue() {
		public float get (Cell cell) {
			return 0;
		}

		public float get (Object table) {
			return 0;
		}
	};

	/** A value that is only valid for use with a cell.
	 * @author Nathan Sweet */
	static abstract public class CellValue extends Value {
		public float get (Object table) {
			throw new UnsupportedOperationException("This value can only be used for a cell property.");
		}
	}

	/** A value that is valid for use with a table or a cell.
	 * @author Nathan Sweet */
	static abstract public class TableValue extends Value {
		public float get (Cell cell) {
			return get(cell.getLayout().getTable());
		}
	}

	/** A fixed value that is not computed each time it is used.
	 * @author Nathan Sweet */
	static public class FixedValue extends Value {
		private float value;

		public FixedValue (float value) {
			this.value = value;
		}

		public void set (float value) {
			this.value = value;
		}

		public float get (Object table) {
			return value;
		}

		public float get (Cell cell) {
			return value;
		}
	}

	/** Value for a cell that is the minWidth of the widget in the cell. */
	static public Value minWidth = new CellValue() {
		public float get (Cell cell) {
			if (cell == null) throw new RuntimeException("minWidth can only be set on a cell property.");
			Object widget = cell.widget;
			if (widget == null) return 0;
			return Toolkit.instance.getMinWidth(widget);
		}
	};

	/** Value for a cell that is the minHeight of the widget in the cell. */
	static public Value minHeight = new CellValue() {
		public float get (Cell cell) {
			if (cell == null) throw new RuntimeException("minHeight can only be set on a cell property.");
			Object widget = cell.widget;
			if (widget == null) return 0;
			return Toolkit.instance.getMinHeight(widget);
		}
	};

	/** Value for a cell that is the prefWidth of the widget in the cell. */
	static public Value prefWidth = new CellValue() {
		public float get (Cell cell) {
			if (cell == null) throw new RuntimeException("prefWidth can only be set on a cell property.");
			Object widget = cell.widget;
			if (widget == null) return 0;
			return Toolkit.instance.getPrefWidth(widget);
		}
	};

	/** Value for a cell that is the prefHeight of the widget in the cell. */
	static public Value prefHeight = new CellValue() {
		public float get (Cell cell) {
			if (cell == null) throw new RuntimeException("prefHeight can only be set on a cell property.");
			Object widget = cell.widget;
			if (widget == null) return 0;
			return Toolkit.instance.getPrefHeight(widget);
		}
	};

	/** Value for a cell that is the maxWidth of the widget in the cell. */
	static public Value maxWidth = new CellValue() {
		public float get (Cell cell) {
			if (cell == null) throw new RuntimeException("maxWidth can only be set on a cell property.");
			Object widget = cell.widget;
			if (widget == null) return 0;
			return Toolkit.instance.getMaxWidth(widget);
		}
	};

	/** Value for a cell that is the maxHeight of the widget in the cell. */
	static public Value maxHeight = new CellValue() {
		public float get (Cell cell) {
			if (cell == null) throw new RuntimeException("maxHeight can only be set on a cell property.");
			Object widget = cell.widget;
			if (widget == null) return 0;
			return Toolkit.instance.getMaxHeight(widget);
		}
	};

	/** Returns a value that is a percentage of the table's width. */
	static public Value percentWidth (final float percent) {
		return new TableValue() {
			public float get (Object table) {
				return Toolkit.instance.getWidth(table) * percent;
			}
		};
	}

	/** Returns a value that is a percentage of the table's height. */
	static public Value percentHeight (final float percent) {
		return new TableValue() {
			public float get (Object table) {
				return Toolkit.instance.getHeight(table) * percent;
			}
		};
	}

	/** Returns a value that is a percentage of the specified widget's width. */
	static public Value percentWidth (final float percent, final Object widget) {
		return new Value() {
			public float get (Cell cell) {
				return Toolkit.instance.getWidth(widget) * percent;
			}

			public float get (Object table) {
				return Toolkit.instance.getWidth(widget) * percent;
			}
		};
	}

	/** Returns a value that is a percentage of the specified widget's height. */
	static public Value percentHeight (final float percent, final Object widget) {
		return new TableValue() {
			public float get (Object table) {
				return Toolkit.instance.getHeight(widget) * percent;
			}
		};
	}
}
