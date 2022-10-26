// React
import { useState } from "react"

// Next
import { useRouter } from "next/router";

// Material UI
import { AppBar, Box, Button, CircularProgress, Container, Paper, TextField, Toolbar, Typography } from "@mui/material"

// Custom
import TaskService from "../../../src/services/TaskService"
import ROUTES from "../../../src/config/routes";
import { useEffect } from "react";

function Task() {

  const router = useRouter()

  const { id } = router.query;

  const [isLoading, setIsLoading] = useState(false)

  const [task, setTask] = useState([])

  const submit = () => {

    const title = document.getElementById("title").value
    const description = document.getElementById("description").value

    if (title === "" || description === "") {
      alert("Please fill in all the fields")
      return
    }

    task.title = title
    task.description = description

    console.log("Task", task)

    TaskService.update(task.id, task).then(() => {
      router.push(ROUTES.tasks.list)
      //   // toast.success(`Article successfully created!`)
    }).catch((e) => console.error(e))
  }

  const reset = () => {
    setTask({
      title: task.title,
      description: task.description
    })
  }

  useEffect(() => {
    if (id) {
      setIsLoading(true)
      TaskService.getById(id).then((res) => {
        setTask(res)
        setIsLoading(false)
      }).catch((e) => {
        setIsLoading(false)
        return console.error(e);
      })
    }
  }, [id])

  useEffect(() => {
    document.getElementById("title").value = task.title
    document.getElementById("description").value = task.description
  }, [task])

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Tasks: Edit
          </Typography>
        </Toolbar>
      </AppBar>

      {isLoading ? (
        <Container>
          <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
            <CircularProgress />
          </Box>
        </Container>
      ) : (
        <Paper sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
          <form>
            <TextField
              variant="outlined"
              label="Title"
              id="title"
              fullWidth
              margin="normal"
            />
            <TextField
              variant="outlined"
              label="Description"
              id="description"
              fullWidth
              margin="normal"
            />
            <Button
              variant="contained"
              color="primary"
              fullWidth
              sx={{ mt: 5 }}
              onClick={submit}
            >
              Save
            </Button>
            <Button
              variant="contained"
              color="error"
              fullWidth
              sx={{ mt: 1 }}
              onClick={reset}
            >
              Reset
            </Button>
          </form>
        </Paper>
      )}
    </Box>
  )
}

export default Task