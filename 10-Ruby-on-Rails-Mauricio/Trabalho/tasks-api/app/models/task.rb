class Task < ApplicationRecord

  scope :completed, -> { where(completed: true) }
  scope :incomplete, -> { where(completed: false) }

end
