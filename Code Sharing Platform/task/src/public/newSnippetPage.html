<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <form id="form">
            <textarea id="code_snippet" class="form-control mb-3"></textarea>
            <button id="send_snippet" type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
    <script>
        function send(event) {
            fetch('/api/code/new', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    "code": document.getElementById("code_snippet").value
                }),
            })
            .then((response) => response.json())
            .then((data) => {
                console.log('Success:', data);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
        }

        const form = document.getElementById("form");
        form.addEventListener("submit", (event) => {
            event.preventDefault();
            send();
        });
    </script>
</body>
</html>