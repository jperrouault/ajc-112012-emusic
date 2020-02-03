let sse = new EventSource('http://localhost:8080/emusic-web/api/produit/sse');
sse.onmessage = (evt) => {
	alert('MSG !!');
	console.log(evt);
};

sse.addEventListener('message', (e) => {
	alert('MSG 2 !!');
	console.log(e);
});