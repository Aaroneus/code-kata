class TestChop

  def self.chop(sought_value, searchable_array = [] )

    mid_index = find_midpoint(searchable_array)

    if mid_index == -1
      return -1
    else
      mid_value = searchable_array[mid_index]
      if mid_value == sought_value
        return mid_index
      elsif mid_value < sought_value
        lower_range = searchable_array.slice(mid_value, searchable_array.length)
        new_index = chop(sought_value, lower_range)
        new_index == -1 ? mid_index + new_index : new_index
      else
        upper_range = searchable_array.slice(0, mid_value)
        new_index = chop(sought_value, upper_range)
        new_index
      end
    end
  end

private

  def self.find_midpoint(array)
    midpoint = round_down(array.length / 2)
    if midpoint == 0
      return -1
    else
      return midpoint
    end
  end
end

