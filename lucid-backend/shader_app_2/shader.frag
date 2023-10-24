precision mediump float;

uniform vec2 resolution;
uniform float time;

void main(){
	vec2 uv = ((gl_FragCoord.xy / resolution) - 1.5) * 2.0;
	uv.x *= resolution.x / resolution.y;
	uv.y -= 0.5;
	
	float d = length(uv);

	d = sin(d * 8.0 + time) / 8.0;
	d = abs(d);
	d = smoothstep(0.0, 0.1, d);

	d = 0.4 / d;

	gl_FragColor = vec4(d, d, d, 1.0);
}
