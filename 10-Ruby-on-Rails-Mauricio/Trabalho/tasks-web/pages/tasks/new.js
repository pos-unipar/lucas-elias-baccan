// React
import { useState } from "react"

// Next
import { useRouter } from "next/router";

// Material UI
import { AppBar, Box, Button, CircularProgress, Container, Paper, TextField, Toolbar, Typography } from "@mui/material"

// Custom
import TaskService from "../../src/services/TaskService"
import ROUTES from "../../src/config/routes";

function Task() {

  const router = useRouter()

  const [isLoading, setIsLoading] = useState(false)


  const submit = () => {

    const title = document.getElementById("title").value
    const description = document.getElementById("description").value

    if (title === "" || description === "") {
      alert("Please fill in all the fields")
      return
    }

    let task = {
      title: title,
      description: description
    }

    TaskService.create(task).then(() => {
      router.push(ROUTES.tasks.list)
      //   // toast.success(`Article successfully created!`)
    }).catch((e) => console.error(e))
  }

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Tasks: New
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
              margin="normal"
              onClick={submit}
            >
              Create
            </Button>
          </form>
        </Paper>
      )}
    </Box>
  )
}

export default Task