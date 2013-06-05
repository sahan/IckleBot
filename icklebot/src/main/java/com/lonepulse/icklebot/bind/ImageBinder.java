package com.lonepulse.icklebot.bind;

/*
 * #%L
 * IckleBot
 * %%
 * Copyright (C) 2013 Lonepulse
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.widget.ImageView;

/**
 * <p>The binding strategy to be employed for {@link ImageView}s. This binding 
 * strategy can be used on a {@link Drawable}, {@link Bitmap}, {@code byte[]}, 
 * {@link Byte}[], or a <b>Base64</b> encoded {@link String}.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ImageBinder extends AbstractBinder<ImageView, Object> {

	/**
	 * <p>Instantiates a new {@link ImageBinder} with the given 
	 * {@link ImageView} and data. 
	 * 
	 * @param imageView
	 * 			the {@link ImageView} to which the data is to be bound
	 * 
	 * @param data
	 * 			the data to be bound; this can be a {@link Drawable}, {@link Bitmap}, 
	 * 			{@code byte[]}, {@link Byte}[] or a Base64 encoded {@link String}  
	 *
	 * @since 1.1.0
	 */
	public ImageBinder(ImageView imageView, Object data) {
	
		super(imageView, data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onBind(ImageView view, Object data) {

		if(data instanceof Drawable) {
			
			view.setImageDrawable((Drawable) data);
		}
		else if(data instanceof Bitmap) {
			
			view.setImageBitmap((Bitmap) data);
		}
		else if(data instanceof String) {
			
			byte[] bytes = Base64.decode((String) data, Base64.DEFAULT);
			Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
			
			view.setImageBitmap(bitmap);
		}
		else if(data instanceof byte[]) {
			
			Bitmap bitmap = BitmapFactory.decodeByteArray((byte[])data, 0, ((byte[])data).length);
			view.setImageBitmap(bitmap);
		}
		else if(data instanceof Byte[]) {
			
			Byte[] wrapperBytes = (Byte[]) data;
			byte[] primitiveBytes = new byte[wrapperBytes.length];
			
			for (int i = 0; i < wrapperBytes.length; i++) {
				
				primitiveBytes[i] = wrapperBytes[i].byteValue();
			}
			
			Bitmap bitmap = BitmapFactory.decodeByteArray(primitiveBytes, 0, primitiveBytes.length);
			view.setImageBitmap(bitmap);
		}
		else {
			
			StringBuilder errorContext = new StringBuilder()
			.append("Type ")
			.append(data.getClass().getName())
			.append(" cannot be bound to an ")
			.append(ImageView.class.getName())
			.append(". Please target a Drawable, Bitmap, byte[], Byte[] or a Base64 encoded String. ");
			
			throw new BindException(errorContext.toString());
		}
	}
}
