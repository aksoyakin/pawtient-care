import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Home from "./components/home/Home.jsx";
import {createBrowserRouter, createRoutesFromElements, Route, Router, RouterProvider} from "react-router-dom";
import RootLayout from "./components/layout/RootLayout.jsx";

function App() {

    const router = createBrowserRouter(createRoutesFromElements(
        <Route path='/' element={<RootLayout/>}>
            <Route index element={<Home/>}/>
        </Route>
    ))

    return (
        <main className="">
            <RouterProvider router={router}/>

        </main>
    );
}

export default App;
