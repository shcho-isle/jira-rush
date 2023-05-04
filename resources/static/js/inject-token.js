var token = localStorage.getItem('token');

if (token) {
    var links = document.querySelectorAll('a');
    for (var i = 0; i < links.length; i++) {
        links[i].addEventListener('click', function(event) {
            event.preventDefault();
            let url = this.getAttribute('href');
            fetch(url, {
                headers: {
                    'Authorization': 'Bearer ' + token
                }
            }).then(function(response) {
                if (response.status === 200) {
                    window.location.replace(url)
                    // window.location.href = url;
                }
            }).catch(function(error) {
                console.log(error);
            });
        });
    }
}