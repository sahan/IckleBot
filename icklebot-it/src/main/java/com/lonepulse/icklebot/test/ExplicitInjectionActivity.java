package com.lonepulse.icklebot.test;

import android.os.Bundle;

import com.lonepulse.icklebot.IckleActivity;
import com.lonepulse.icklebot.annotation.Layout;
import com.lonepulse.icklebot.annotation.Title;

/**
 * <p>An extension of {@link IckleActivity} which is used to test the 
 * <b>explicit runtime injection</b> features of IckleBot.
 * 
 * @category test
 * <br><br>
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Layout(R.layout.act_explicit_injection)
@Title(id = R.string.ttl_act_explicit_injection)
public class ExplicitInjectionActivity extends IckleActivity {
	

	/**
	 * <p>Exposes {@link #onCreate(Bundle)} and allows unit 
	 * tests to invoke injection from an external context.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}
}
