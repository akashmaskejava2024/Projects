const Dashboard = () => {

    const getHelloFormServer = () => {
        const token = sessionStorage.getItem("token");
        if (token) {
            const URL = 'http://localhost:8080/hello';
            fetch(URL, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`,  // Send the token in Authorization header
                    'Content-Type': 'application/json'
                }
            })
            .then(res => {
                // Check if the response is OK (status code 200-299)
                if (!res.ok) {
                    throw new Error('Network response was not ok');
                }
                return res.json();  // Parse the JSON response
            })
            .then(data => {
                console.log(data);  // Handle the parsed JSON response here
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
            });
        } else {
            console.log('No token found');
        }
    };
    
    return (
        <>
            <h1>Dashboard hello test</h1>
            <button className="btn btn-success" id="btn" onClick={getHelloFormServer}>get test Hello </button>
        </>
    );
}

export default Dashboard;
