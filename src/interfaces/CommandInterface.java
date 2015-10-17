package interfaces;



import java.util.HashMap;

public interface CommandInterface {
	public boolean execute(HashMap<String,String> pRequestParameters,HashMap<String,Object> pRequestStorage,HashMap<String,Object> pResponseStorage) throws Exception;
}
