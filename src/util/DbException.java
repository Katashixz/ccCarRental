package util;

public class DbException extends BaseException {
	public DbException(Throwable ex){
		super("���ݿ��������"+ex.getMessage());
	}
}
