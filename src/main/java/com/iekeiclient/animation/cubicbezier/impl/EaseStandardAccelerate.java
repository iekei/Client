package com.iekeiclient.animation.cubicbezier.impl;

import com.iekeiclient.animation.cubicbezier.CubicBezier;

public class EaseStandardAccelerate extends CubicBezier {

	public EaseStandardAccelerate(float duration, float start, float end) {
		super(0.3F, 0F, 1F, 1F, duration, start, end);
	}
}
