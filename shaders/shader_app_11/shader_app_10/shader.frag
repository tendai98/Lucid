precision mediump float;

uniform vec2 resolution;
uniform float time;

vec4 palette(float t){
        vec4 a = vec4(0.5, 0.5, 0.5, 1.0);
        vec4 b = vec4(0.5, 0.5, 0.5, 1.0);
        vec4 c = vec4(1.0, 1.0, 1.0, 1.0);  
        vec4 d = vec4(0.263, 0.416, 0.557, 1.0);

        return a + b * cos(6.28318 * (c*t+d));
}


void main(){
	vec2 uv = ((gl_FragCoord.xy / resolution) - 0.5) * 2.0;
	uv.x *= resolution.x / resolution.y;
	vec2 uv0 = uv;
	vec4 finalColor = vec4(0.0);

	for(float i = 0.0; i < 3.0; i++){
		uv = fract(uv * 2.0) - 0.5;

		float d = length(uv) * sin(time);
		vec4 computeColor = palette(length(uv0) - sin(time * 2.0));

		d = sin(d*4.0 + time) / 8.0;
		d = abs(d);
		d = 0.01 / d;

		finalColor += computeColor * d;
	}

	gl_FragColor = finalColor;
}
