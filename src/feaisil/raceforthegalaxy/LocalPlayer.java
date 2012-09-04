/**
 * 
 */
package feaisil.raceforthegalaxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;

import feaisil.raceforthegalaxy.common.Reply;
import feaisil.raceforthegalaxy.common.Request;

/**
 * @author P. MEULEMAN
 *
 */
public final class LocalPlayer extends Player {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void submitRequestImpl(Request iReq)
	{
		byte buffer[] = new byte[10];
		System.out.print(iReq.getQueryText());
		try {
			System.in.read(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reply aRep = new Reply();
		
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(inputStreamReader);
		String aBuffer = "";
		try {
			aBuffer = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		aRep.setReplyText(aBuffer);
		aRep.setProcessingDone(true);

		this.setChanged();
		notifyObservers(aRep);
	}
	
	@Override
	public String toString() {
		StringBuilder _builder = new StringBuilder();
		_builder.append("LocalPlayer [");
		if (super.toString() != null) {
			_builder.append("toString()=");
			_builder.append(super.toString());
		}
		_builder.append("]");
		return _builder.toString();
	}
}
