package cn.ldbz.exception;

/**
 * 业务异常基类，所有业务异常都必须继承于此异常 
 * 
 */
public class BasisException extends RuntimeException {

    /**
     * 异常信息
     */
    protected String msg;

    /**
     * 具体异常码
     */
    protected int code;

    public BasisException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
    }

    public BasisException() {
        super();
    }

    public BasisException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasisException(Throwable cause) {
        super(cause);
    }

    public BasisException(String message) {
        super(message);
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    /**
     * 实例化异常
     * 
     * @param msgFormat
     * @param args
     * @return
     */
    public BasisException newInstance(String msgFormat, Object... args) {
        return new BasisException(this.code, msgFormat, args);
    }

}
