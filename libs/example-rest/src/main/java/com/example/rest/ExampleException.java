package com.example.rest;

public class ExampleException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6503266726272509124L;

	/**
	 * 錯誤類型
	 */
	public enum ExampleErrorType implements IErrorType {

		/**
		 * 系統錯誤
		 */
		EXAMPLE_SYSTEM_ERROR("99999", ErrorLevel.HIGH),

		/**
		 * 未知系統錯誤
		 */
		EXAMPLE_UNKNOW_SYSTEM_ERROR("99998", ErrorLevel.MEDIUM),

		/**
		 * 資料庫逾時
		 */
		EXAMPLE_DB_TIME_OUT("99997", ErrorLevel.MEDIUM),

		/**
		 * 系統低級失敗
		 */
		EXAMPLE_LOW_FAIL("99980", ErrorLevel.LOW);

		private String errorCode;

		private ErrorLevel errorLevel;

		ExampleErrorType(String errorCode, ErrorLevel errorLevel) {
			this.errorCode = errorCode;
			this.errorLevel = errorLevel;
		}

		@Override
		public String getErrorCode() {
			return errorCode;
		}

		@Override
		public ErrorLevel getErrorLevel() {
			return errorLevel;
		}
	}

	/**
	 * 錯誤類型
	 */
	protected IErrorType errorType;

	/**
	 * 用於顯示給使用者之錯誤訊息
	 */
	protected String displayErrorMessage;

	/**
	 * @param exceptionMsg 錯誤訊息
	 */
	public ExampleException(String errorMsg) {
		super(errorMsg);
		this.errorType = ExampleErrorType.EXAMPLE_SYSTEM_ERROR;
		this.displayErrorMessage = errorMsg;
	}

	/**
	 * @param errorType    錯誤類型
	 * @param exceptionMsg 錯誤訊息
	 */
	public ExampleException(IErrorType errorType, String errorMsg) {
		super(errorMsg);
		this.errorType = errorType;
		this.displayErrorMessage = errorMsg;
	}

	public IErrorType getErrorType() {
		return errorType;
	}

	public String getDisplayErrorMessage() {
		return displayErrorMessage;
	}

	@Override
	public String getMessage() {
		return "[" + errorType.getErrorCode() + "]" + super.getMessage() + "|"
				+ errorType.getErrorLevel();
	}
}
