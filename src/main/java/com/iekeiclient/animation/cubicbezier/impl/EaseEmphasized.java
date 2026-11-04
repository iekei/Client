package com.iekeiclient.animation.cubicbezier.impl;

import com.iekeiclient.animation.cubicbezier.CubicBezier;

public class EaseEmphasized extends CubicBezier {

	public EaseEmphasized(float duration, float start, float end) {
		super(0.2F, 0F, 1F, 1F, duration, start, end);
	}
}
