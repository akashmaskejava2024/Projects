import React from 'react';
import "./dashboard.css";
import { getHelloFormServer } from "../ActionCreator/UserActionCreator";

const Dashboard = () => {
    return (
        <>
            <nav className="navbar navbar-expand-lg bg-body-tertiary">
                <div className="container-fluid">
                    <a className="navbar-brand" href="#">TodoList</a>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                        aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                            {/* <li className="nav-item">
                                <a className="nav-link disabled" aria-disabled="true">Disabled</a>
                            </li> */}
                        </ul>
                        {/* <form className="d-flex" role="search">
                            <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                            <button className="btn btn-outline-success" type="submit">Search</button>
                        </form> */}
                        <button className="btn btn-outline-success" type="submit">Logout</button>
                    </div>
                </div>
            </nav>

            <div className="d-flex">
                <div className="listContainer p-2 w-10 ">
                    <div className="d-flex">
                        <button className="btn btn-outline-success d-flex" data-bs-toggle="modal" data-bs-target="#CreateTaskModal"
                            data-bs-whatever="@fat">
                            Create Task
                        </button>
                    </div>
                    <div className="d-flex p-2">Lists</div>
                    <ul className="list-group">
                        <li className="list-group-item p-2">
                            <input className="form-check-input me-1" type="checkbox" id="firstCheckbox" />
                            <label className="form-check-label" htmlFor="firstCheckbox">First checkbox</label>
                        </li>
                        <li className="list-group-item p-2">
                        <div className="d-flex">
                        <button className="btn btn-outline-success d-flex" data-bs-toggle="modal" data-bs-target="#CreatelistModal"
                            data-bs-whatever="@fat">
                            Create List
                        </button>
                    </div>
                        </li>
                    </ul>
                </div>

                <div className="TaskContianer p-2 w-100">
                    <div className="card-container">
                        <div className="card h-100">
                            <div className="card-body">
                                <h5 className="card-title">List 1</h5>
                                <p className="card-text">
                                    <ul className="list-group">
                                        <li className="list-group-item p-2">
                                            <button className="btn p-1">Add Task</button>
                                        </li>
                                        <li className="list-group-item p-2">
                                            <input className="form-check-input me-1" type="checkbox" id="firstCheckbox" />
                                            <label className="form-check-label" htmlFor="firstCheckbox">First checkbox</label>
                                            <div className='d-flex px-4'>
                                                <label className="form-check-label">Date</label>
                                            </div>
                                        </li>
                                    </ul>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            {/* Task Addition main Modal */}
            <div className="modal fade" id="CreateTaskModal" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <form>
                            <div className="modal-header">
                                <h1 className="modal-title fs-5" id="exampleModalLabel">New Task</h1>
                                <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div className="modal-body">
                                <div className="mb-3">
                                    <input type="text" className="form-control" id="task_name" name="task_name" placeholder="Task Name" />
                                </div>
                                <div className="mb-3">
                                    <textarea className="form-control" id="task_desc" name="task_desc" placeholder="Task Description"></textarea>
                                </div>
                                <div className="mb-3">
                                    <input type="date" className="form-control" id="task_date" name="task_date" />
                                </div>
                                <div className="mb-3">
                                    <div className="dropdown">
                                        <button className="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                            List
                                        </button>
                                        <ul className="dropdown-menu">
                                            <li><a className="dropdown-item" href="#">Action</a></li>
                                            <li><a className="dropdown-item" href="#">Another action</a></li>
                                            <li><a className="dropdown-item" href="#">Something else here</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div className="modal-footer">
                                <button type="submit" className="btn btn-primary">Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>


            {/* Task Addition main Modal */}
            <div className="modal fade" id="CreatelistModal" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <form>
                            <div className="modal-header">
                                <h1 className="modal-title fs-5" id="exampleModalLabel">New List</h1>
                                <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div className="modal-body">
                                <div className="mb-3">
                                    <input type="text" className="form-control" id="list_name" name="list_name" placeholder="list Name" />
                                </div>
                            </div>
                            <div className="modal-footer">
                                <button type="submit" className="btn btn-primary">Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>



        </>
    );
};

export default Dashboard;
