// React
import { useEffect, useState } from "react"

// Next.js
import Link from "next/link";
import { useRouter } from "next/router";

// Material UI
import { Add, CheckBox, CheckBoxOutlineBlank } from '@mui/icons-material'
import { AppBar, Toolbar, Typography, Fab, Table, TableHead, TableRow, TableCell, TableBody, Button, CircularProgress } from '@mui/material'
import { Box, Container } from '@mui/system'
import TaskService from "../../src/services/TaskService"

// Custom
import ROUTES from "../../src/config/routes";

function Home() {

  const { router } = useRouter();

  const [isLoading, setIsLoading] = useState(true)
  const [tasks, setTasks] = useState([])

  const getAll = async () => {
    let data = await TaskService.getAll()
    console.log(data)
    setTasks(data)
  }

  const deleteTask = (task) => {
    var confirm = window.confirm(`Are you sure you want to delete the task with \nID: ${task.id}\nTitle: ${task.title}\n?`)
    if (confirm) {
      TaskService.destroy(task.id).then(() => {
        getAll()
      }).catch((e) => console.error(e))
    }
  }

  const toggleTask = (task) => {
    TaskService.update(task.id, { completed: !task.completed }).then(() => {
      getAll()
    }).catch((e) => console.error(e))
  }

  useEffect(() => {
    getAll()
    setIsLoading(false)
  }, [])

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Tasks
          </Typography>
        </Toolbar>
      </AppBar>

      <Container>
        {isLoading ? (
          <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
            <CircularProgress />
          </Box>
        ) : (
          <Table>

            <TableHead>
              <TableRow>
                <TableCell>Completed</TableCell>
                <TableCell>Task</TableCell>
                <TableCell>Description</TableCell>
                <TableCell>Completed at</TableCell>
                <TableCell>Actions</TableCell>
              </TableRow>
            </TableHead >

            <TableBody>
              {tasks.map((task) => (
                <TableRow key={task.id}>
                  <TableCell>
                    <Button
                      variant="contained"
                      color={task.completed ? "success" : "info"}
                      onClick={() => toggleTask(task)}
                    >
                      {task.completed ? <CheckBox /> : <CheckBoxOutlineBlank />}
                    </Button>
                  </TableCell>
                  <TableCell>{task.title}</TableCell>
                  <TableCell>{task.description}</TableCell>
                  <TableCell>{task.completed_at ? new Date(task.completed_at).toLocaleString() : ""}</TableCell>
                  <TableCell>
                    <Button
                      variant="contained">
                      <Link
                        href={{
                          pathname: ROUTES.tasks.edit,
                          query: {
                            id: task.id,
                          },
                        }}
                      >
                        Edit
                      </Link>
                    </Button>
                    <Button
                      variant="contained"
                      color="error"
                      onClick={() => deleteTask(task)}>
                      Delete
                    </Button>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>

          </Table >
        )}

        <Fab color="primary" aria-label="add" sx={{ position: 'absolute', bottom: 25, right: 25 }}>
          <Link
            href={{
              pathname: ROUTES.tasks.new,
            }}
          >
            <Add />
          </Link>
        </Fab>

      </Container >
    </Box >
  )
}

export default Home