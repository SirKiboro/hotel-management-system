import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap.bundle.min.js"
import AddRoom from "./components/room/AddRoom"

const App = () => {
    return (
        <div className="container py-4">
            <AddRoom />
        </div>
    )
}

export default App
