import axiosInstance from "../utils/axios";

const TaskService = {
  getAll: async () => {
    let response = await axiosInstance.get('/tasks')
    return response.data
  },
  getById: async (id) => {
    if (!id) return

    let response = await axiosInstance.get(`/tasks/${id}`)
    return response.data
  },
  create: async (task) => {
    if (!task) return

    let response = await axiosInstance.post(`/tasks`, { task: task })
    return response.data
  },
  destroy: async (id) => {
    if (!id) return

    let response = await axiosInstance.delete(`/tasks/${id}`)
    return response.data
  },
  update: async (id, task) => {
    if (!id && !task) return

    delete task.id
    delete task.created_at
    delete task.updated_at

    let response = await axiosInstance.put(`/tasks/${id}`, { task: task })
    return response.data
  }

}

export default TaskService;